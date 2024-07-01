package org.example.shoppingcart.service.Impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import org.example.common.consts.AppHttpCodeEnum;
import org.example.common.exception.CustomException;
import org.example.rediscommon.common.RedisPrefix;
import org.example.rediscommon.util.RedisUtils;
import org.example.shoppingcart.mapper.ShoppingCartMapper;
import org.example.shoppingcart.model.BasSkuEntity;
import org.example.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ShoppingCartMapper mapper;

    @Override
    public Map<String, Integer> getList(String userName, String skuName) {
        String key = RedisPrefix.CART_INFO+":"+userName;
        Map<String, Integer> map = new HashMap<>();
        if (skuName != null && !StringUtils.isBlank(skuName)) {
            List<BasSkuEntity> skuList = mapper.getSkuList(skuName);
            for (BasSkuEntity sku : skuList) {
                Integer qty = redisUtils.getHashValue(key, sku.getSku());
                if (qty != null) {
                    map.put(sku.getSkuName(), qty);
                }
            }
        } else {
            for (Map.Entry<String, Integer> entry : redisUtils.getHash(key).entrySet()) {
                map.put(mapper.getSkuName(entry.getKey()), entry.getValue());
            }
        }
        return map;
    }

    @Override
    public long incrSku(String userName, String sku) {
        String key = RedisPrefix.CART_INFO+":"+userName;
        if (redisUtils.hasHashKey(key, sku)) {
            return redisUtils.incrForHash(key, sku);
        } else {
            redisUtils.hSet(key, sku, 1);
            return 1;
        }
    }

    @Override
    public long decrSku(String userName, String sku) {
        String key = RedisPrefix.CART_INFO+":"+userName;
        Integer qty = redisUtils.getHashValue(key, sku);
        if (qty == null) {
            throw new CustomException(AppHttpCodeEnum.SKU_NOT_EXISTS);
        }
        if (qty == 1) {
            throw new CustomException(AppHttpCodeEnum.QTY_LEAST);
        }
        return redisUtils.decrForHash(key, sku);
    }

    @Override
    public void delSku(String userName, String sku) {
        String key = RedisPrefix.CART_INFO+":"+userName;
        if (redisUtils.delHashKey(key, sku) == 0) {
            throw new CustomException(AppHttpCodeEnum.SKU_NOT_EXISTS);
        }
    }

    @Override
    public void clear(String userName) {
        String key = RedisPrefix.CART_INFO+":"+userName;
        if (!redisUtils.delete(key)) {
            throw new CustomException(AppHttpCodeEnum.CART_EMPTY);
        }
    }
}
