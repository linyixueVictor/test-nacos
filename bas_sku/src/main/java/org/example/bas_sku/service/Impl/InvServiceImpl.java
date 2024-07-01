package org.example.bas_sku.service.Impl;

import org.example.bas_sku.mapper.InvMapper;
import org.example.bas_sku.service.InvService;
import org.example.common.ConstA;
import org.example.common.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvServiceImpl implements InvService {
    @Autowired
    InvMapper mapper;
    @Override
    public void descStock(String sku, Long qty) {
        int rows = mapper.descStock(sku, qty);
        if (rows == 0) {
            throw new CustomException(500, ConstA.ErrorMsg.BasSku.NOT_ENOUGH);
        }
    }
}
