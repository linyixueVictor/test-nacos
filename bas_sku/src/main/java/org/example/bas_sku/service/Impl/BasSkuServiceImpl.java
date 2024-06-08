package org.example.bas_sku.service.Impl;

import org.example.bas_sku.dao.BasSkuMapper;
import org.example.bas_sku.service.BasSkuService;
import org.example.common.Const;
import org.example.common.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasSkuServiceImpl implements BasSkuService {
    @Autowired
    BasSkuMapper basSkuMapper;
    @Override
    public Character getFlagById(String sku) {
        Character fifoFlag;
        try {
            fifoFlag = basSkuMapper.getFlagById(sku);
        } catch (Exception e) {
            throw new CustomException(500, Const.ErrorMsg.BasSku.GET);
        }
        if (fifoFlag == null) {
            throw new CustomException(500, Const.ErrorMsg.BasSku.NOT_EXIST);
        }
        return fifoFlag;
    }
}
