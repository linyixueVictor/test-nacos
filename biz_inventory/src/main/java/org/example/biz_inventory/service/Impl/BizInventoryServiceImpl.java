package org.example.biz_inventory.service.Impl;

import org.example.biz_inventory.entity.BizInventory;
import org.example.biz_inventory.feign.BasSkuFeign;
import org.example.biz_inventory.dao.BizInventoryMapper;
import org.example.biz_inventory.service.BizInventoryService;
import org.example.common.Const;
import org.example.common.CustomException;
import org.example.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class BizInventoryServiceImpl implements BizInventoryService {
    @Autowired
    BizInventoryMapper bizInventoryMapper;
    @Autowired
    BasSkuFeign basSkuFeign;
    @Autowired
    ReentrantLock lock;

    @Override
    public List<BizInventory> getList() {
        try {
            List<BizInventory> list = bizInventoryMapper.getList();
            return list;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deduct(String sku, Integer qty, Long editWho) {
        //查询物料号是否存在，存在则查是否先进先出物料
        R result = basSkuFeign.getFlagById(sku);
        CopyOnWriteArrayList<Long> invIds;
        if (!result.get("code").equals(200)) {
            throw new CustomException(500, (String)result.get("msg"));
        }
        int qtyTmp = qty;
        //获取库存Id集合
        invIds = result.get("data").equals("Y") ?
                bizInventoryMapper.getFifoBySku(sku) : bizInventoryMapper.getBySku(sku);
        if (invIds.isEmpty()) {
            throw new CustomException(500, Const.ErrorMsg.BizInventory.NOT_ENOUGH);
        }
        doDeduct(invIds, qtyTmp, editWho);
    }

    @Transactional
    public void doDeduct(CopyOnWriteArrayList<Long> invIds, Integer qtyTmp, Long editWho) {
        for (Long invId : invIds) {
            try {
                lock.lock();
                //获取当前库存
                int invQty = bizInventoryMapper.getQtyById(invId);
                if (invQty == 0) {
                    invIds.remove(invId);
                    continue;
                }
                if (invIds.size() == 1 && qtyTmp > invQty) {
                    throw new CustomException(500, Const.ErrorMsg.BizInventory.NOT_ENOUGH);
                }
                //获取该条库存应扣减数量
                int toQty = Math.min(invQty, qtyTmp);
                //扣减库存
                bizInventoryMapper.deduct(invId, toQty, editWho);
                qtyTmp -= toQty;
                if (qtyTmp == 0) {
                    break;
                }
            } catch (CustomException e) {
                throw new CustomException(e.getCode(), e.getMsg());
            } catch (Exception e) {
                throw new CustomException(500, Const.ErrorMsg.BizInventory.DEDUCT, e.getMessage());
            } finally {
                lock.unlock();
            }
        }
        if (qtyTmp > 0) {
            throw new CustomException(500, Const.ErrorMsg.BizInventory.NOT_ENOUGH);
        }
    }
}
