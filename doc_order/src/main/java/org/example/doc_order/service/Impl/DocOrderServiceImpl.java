package org.example.doc_order.service.Impl;

import org.example.common.consts.AppHttpCodeEnum;
import org.example.common.consts.Operation;
import org.example.common.exception.CustomException;
import org.example.common.utils.KafkaLogsUtils;
import org.example.doc_order.mapper.DocOrderMapper;
import org.example.doc_order.module.BasSkuEntity;
import org.example.doc_order.module.DocOrderDetails;
import org.example.doc_order.module.DocOrderHeader;
import org.example.doc_order.service.DocOrderService;
import org.example.rediscommon.common.RedisPrefix;
import org.example.rediscommon.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocOrderServiceImpl implements DocOrderService {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    DocOrderMapper mapper;
    @Autowired
    private KafkaLogsUtils logsUtils;

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
    public DocOrderHeader getInfo(Long orderNo, String userName) {
        DocOrderHeader header = mapper.getInfo(orderNo);
        if (header == null) {
            logsUtils.sendLogs(userName, Operation.ORDER_GETINFO, AppHttpCodeEnum.ORDER_NOT_EXISTS);
            throw new CustomException(AppHttpCodeEnum.ORDER_NOT_EXISTS);
        }
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
                logsUtils.sendLogs(userName, Operation.GENERATE_ORDER, AppHttpCodeEnum.SKU_NOT_EXISTS);
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
    public void out(Long orderNo, String userName) {
        int rows = mapper.out(orderNo);
        if (rows == 0) {
            logsUtils.sendLogs(userName, Operation.ORDER_GETINFO, AppHttpCodeEnum.ORDER_NOT_EXISTS);
            throw new CustomException(AppHttpCodeEnum.ORDER_NOT_EXISTS);
        }
    }

    @Override
    public void received(Long orderNo, String userName) {
        int rows = mapper.received(orderNo);
        if (rows == 0) {
            logsUtils.sendLogs(userName, Operation.RECEIVED, AppHttpCodeEnum.ORDER_NOT_EXISTS);
            throw new CustomException(AppHttpCodeEnum.ORDER_NOT_EXISTS);
        }
    }

    @Override
    public void close(Long orderNo, String userName) {
        int rows = mapper.close(orderNo);
        if (rows == 0) {
            logsUtils.sendLogs(userName, Operation.CLOSE, AppHttpCodeEnum.ORDER_NOT_EXISTS);
            throw new CustomException(AppHttpCodeEnum.ORDER_NOT_EXISTS);
        }
    }

    @Override
    public void delete(Long orderNo, String userName) {
        int rows = mapper.delete(orderNo);
        if (rows == 0) {
            logsUtils.sendLogs(userName, Operation.DELETE, AppHttpCodeEnum.ORDER_NOT_EXISTS);
            throw new CustomException(AppHttpCodeEnum.ORDER_NOT_EXISTS);
        }
    }
}
