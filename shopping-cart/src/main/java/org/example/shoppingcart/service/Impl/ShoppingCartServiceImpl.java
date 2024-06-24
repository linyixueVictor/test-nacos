package org.example.shoppingcart.service.Impl;

import org.example.common.Const;
import org.example.common.exception.CustomException;
import org.example.rediscommon.common.RedisPrefix;
import org.example.rediscommon.util.RedisUtils;
import org.example.shoppingcart.mapper.ShoppingCartMapper;
import org.example.shoppingcart.model.ShoppingCart;
import org.example.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ShoppingCartMapper mapper;

    @Override
    public List<ShoppingCart> getList(String userName, String skuName) {
        return mapper.getList(userName, skuName);
    }

    @Override
    public long incrSku(String userName, String sku) {
        mapper.
        if (redisUtils.hasKey(key)) {
            long value = redisUtils.incr(key);
            mapper.updateQty(userName, sku, value);
            return value;
        } else {
            redisUtils.set(key, 1);
            mapper.addSku(userName, sku);
            return 1;
        }
    }

    @Override
    public long decrSku(String userName, String sku) {
        String key = RedisPrefix.CART_INFO+"."+userName+":"+sku;
        String valueStr = redisUtils.get(key);
        if (valueStr == null) {
            throw new CustomException(500, Const.ErrorMsg.ShoppingCart.NOT_EXISTS);
        }
        long value = Long.parseLong(valueStr);
        if (value == 1) {
            throw new CustomException(500, Const.ErrorMsg.ShoppingCart.QTY_LEAST);
        }
        value = redisUtils.decr(key);
        mapper.updateQty(userName, sku, value);
        return value;
    }

    @Override
    public void delSku(String userName, String sku) {
        String key = RedisPrefix.CART_INFO+"."+userName+":"+sku;
        if (!redisUtils.delete(key)) {
            throw new CustomException(500, Const.ErrorMsg.ShoppingCart.NOT_EXISTS);
        }
        mapper.deleteSku(userName, sku);
    }

    @Override
    public long clear(String userName) {
        return 0;
    }
}
