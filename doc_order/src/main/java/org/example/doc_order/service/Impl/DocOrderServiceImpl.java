package org.example.doc_order.service.Impl;

import org.example.common.AppHttpCodeEnum;
import org.example.common.exception.CustomException;
import org.example.doc_order.mapper.DocOrderMapper;
import org.example.doc_order.module.BasSkuEntity;
import org.example.doc_order.module.DocOrderDetails;
import org.example.doc_order.module.DocOrderHeader;
import org.example.doc_order.service.DocOrderService;
import org.example.rediscommon.common.RedisPrefix;
import org.example.rediscommon.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocOrderServiceImpl implements DocOrderService {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    DocOrderMapper mapper;

    @Override
    public List<DocOrderHeader> getList(String userName, Integer status, String skuName,
                                        String keeperName) {
        List<DocOrderHeader> list = mapper.getList(userName, status, skuName, keeperName);
        for (DocOrderHeader header : list) {
            List<DocOrderDetails> details = mapper.getDetailsListById(header.getOrderNo());
            header.setDetails(details);
        }
        return list;
    }

    @Override
    public DocOrderHeader getInfo(Long orderNo) {
        DocOrderHeader header = mapper.getInfo(orderNo);
        List<DocOrderDetails> details = mapper.getDetailsListById(orderNo);
        header.setDetails(details);
        return header;
    }

    @Override
    public void generateOrder(String userName, String skus) {
        String[] skuArr = skus.split(",");
        String key = RedisPrefix.CART_INFO+":"+userName;
        for (String sku : skuArr) {
            BasSkuEntity skuEntity = mapper.getById(sku);
            if (skuEntity == null) {
                throw new CustomException(AppHttpCodeEnum.SKU_NOT_EXISTS);
            }
            Long orderNo = mapper.getOrderNoByKeeperId(skuEntity.getKeeperId());
            if (orderNo == null) {
                DocOrderHeader header = new DocOrderHeader();
                header.setUserName(userName);
                mapper.addHeader(header);
            }
            Long qty = redisUtils.getHashValue(key, sku);
            DocOrderDetails details = new DocOrderDetails();
            details.setOrderNo(orderNo);
            details.setSku(sku);
            details.setSkuName(skuEntity.getSkuName());
            if (qty == null) {
                //点击立即购买
                details.setQty(1L);
            } else {
                //从购物车购买
                details.setQty(qty);
            }
            mapper.addDetails(details);
        }
    }

    @Override
    public void out(Long orderNo) {
        mapper.out(orderNo);
    }

    @Override
    public void received(Long orderNo) {
        mapper.received(orderNo);
    }

    @Override
    public void close(Long orderNo) {
        mapper.close(orderNo);
    }

    @Override
    public void delete(Long orderNo) {
        mapper.delete(orderNo);
    }
}
