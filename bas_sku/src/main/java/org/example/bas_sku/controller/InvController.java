package org.example.bas_sku.controller;

import org.example.bas_sku.service.InvService;
import org.example.common.AppHttpCodeEnum;
import org.example.common.Const;
import org.example.common.R;
import org.example.common.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inv")
public class InvController {
    @Autowired
    InvService service;
    @PostMapping("/descStock")
    public R<Long> descStock(@RequestParam String sku, @RequestParam Long qty) {
        service.descStock(sku, qty);
        return R.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
