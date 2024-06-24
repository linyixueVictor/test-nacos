package org.example.bas_sku.controller;

import org.example.bas_sku.model.BasSkuEntity;
import org.example.bas_sku.service.BasSkuService;
import org.example.common.AppHttpCodeEnum;
import org.example.common.Const;
import org.example.common.exception.CustomException;
import org.example.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sku")
public class BasSkuController {
    @Autowired
    BasSkuService basSkuService;
    @GetMapping("/getById")
    public R<BasSkuEntity> getById(@RequestParam String sku) {
        BasSkuEntity entity = basSkuService.getById(sku);
        return R.okResult(AppHttpCodeEnum.SUCCESS_GET);
    }
}
