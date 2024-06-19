package org.example.bas_sku.service.Impl;

import org.example.bas_sku.mapper.BasSkuMapper;
import org.example.bas_sku.model.BasSkuEntity;
import org.example.bas_sku.service.BasSkuService;
import org.example.common.Const;
import org.example.common.exception.CustomException;
import org.example.rediscommon.common.RedisPrefix;
import org.example.rediscommon.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BasSkuServiceImpl implements BasSkuService {
    @Autowired
    BasSkuMapper mapper;
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public BasSkuEntity getById(String sku) {
        String redisSkuKey = RedisPrefix.SKU_INFO + sku;
        BasSkuEntity entity = redisUtils.get(redisSkuKey, BasSkuEntity.class);
        if (entity == null) {
            entity = mapper.getById(sku);
            if (entity == null) {
                throw new CustomException(500, Const.ErrorMsg.BasSku.NOT_EXIST);
            }
            redisUtils.set(redisSkuKey, entity, 60*60, TimeUnit.SECONDS);
        } else {
            redisUtils.expire(redisSkuKey, 60*60, TimeUnit.SECONDS);
        }
        return entity;
    }
}
