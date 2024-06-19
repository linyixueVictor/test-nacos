package org.example.doc_order.service.Impl;

import org.example.common.Const;
import org.example.common.R;
import org.example.common.exception.CustomException;
import org.example.doc_order.model.DocOrderDetails;
import org.example.doc_order.model.DocOrderEntity;
import org.example.doc_order.feign.BizInventoryFeign;
import org.example.doc_order.mapper.DocOrderMapper;
import org.example.doc_order.service.DocOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocOrderServiceImpl implements DocOrderService {
    @Autowired
    DocOrderMapper mapper;
    @Autowired
    BizInventoryFeign bizInventoryFeign;
    @Override
    public DocOrderEntity getInfo(String orderNo) {
        DocOrderEntity entity = mapper.getOrderInfo(orderNo);
        if (entity == null) {
            throw new CustomException(500, Const.ErrorMsg.DocOrder.NOT_EXIST);
        }
        List<DocOrderDetails> list = getOrderDetails(orderNo, null);
        entity.setDocOrderDetails(list);
        return entity;
    }

    /**
     * 创建订单头
     * @param entity
     */
    @Override
    public void addHeader(DocOrderEntity entity) {
        mapper.addHeader(entity);
    }

    /**
     * 创建订单明细
     * @param entity
     */
    @Override
    public void addDetails(DocOrderDetails entity) {
        mapper.addDetails(entity);
    }

    /**
     * 发运
     * @param shipType
     * @param orderNo
     */
    @Override
    @Transactional
    public void ship(String shipType, String orderNo, String sku, String outWho) {
        List<DocOrderDetails> details;
        if ("byOrder".equals(shipType)) {
            details = getOrderDetails(orderNo, null);
        } else if ("bySku".equals(shipType)) {
            details = getOrderDetails(orderNo, sku);
        } else {
            throw new CustomException(500, Const.ErrorMsg.DocOrder.MODE_ERROR);
        }
        if (details.isEmpty()) {
            throw new CustomException(500, Const.ErrorMsg.DocOrder.DETAILS_EMPTY);
        }
        int i = 0;
        for (DocOrderDetails item : details) {
            //扣减库存
            R result = bizInventoryFeign.deduct(item.getSku(), item.getQty(), "abc");
            if (!result.get("code").equals(200)) {
                throw new CustomException(500, (String) result.get("msg"));
            }
            int rows = mapper.updateDetailsStatus(orderNo, item.getSku(), outWho);
            if (rows == 0) {
                throw new CustomException(500, Const.ErrorMsg.DocOrder.DETAILS_NOT_EXIST);
            }
            //第一次发运时更新状态和时间
            if (i == 0) {
                rows = mapper.updateHeaderFirst(orderNo, outWho);
                if (rows == 0) {
                    throw new CustomException(500, Const.ErrorMsg.DocOrder.NOT_EXIST);
                }
                i++;
            }
        }
        //最后一次发运时更新状态和发运时间
        int count = mapper.getCreateStatusDetailsCount(orderNo);
        if (count == 0) {
            int rows = mapper.updateHeaderLast(orderNo, outWho);
            if (rows == 0) {
                throw new CustomException(500, Const.ErrorMsg.DocOrder.NOT_EXIST);
            }
        }
    }

    private List<DocOrderDetails> getOrderDetails(String orderNo, String sku) {
        List<DocOrderDetails> list = mapper.getOrderDetailsList(orderNo, sku);
        if (list == null || list.isEmpty()) {
            throw new CustomException(500, Const.ErrorMsg.DocOrder.DETAILS_EMPTY);
        }
        return list;
    }
}
