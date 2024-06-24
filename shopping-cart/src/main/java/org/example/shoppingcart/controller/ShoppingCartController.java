package org.example.shoppingcart.controller;

import org.example.common.AppHttpCodeEnum;
import org.example.common.Const;
import org.example.common.R;
import org.example.common.exception.CustomException;
import org.example.shoppingcart.model.ShoppingCart;
import org.example.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService service;

    @GetMapping("/getList")
    public R<List<ShoppingCart>> getList(@RequestParam String userName,
                                         @RequestParam(required = false) String skuName) {
        List<ShoppingCart> list = service.getList(userName, skuName);
        return R.okResult(AppHttpCodeEnum.SUCCESS_GET, list);
    }

    @PostMapping("/incrSku")
    public R<Long> incrSku(@RequestParam String sku, @RequestParam String userName) {
        long result = service.incrSku(userName, sku);
        return R.okResult(AppHttpCodeEnum.SUCCESS, result);
    }

    @PostMapping("/decrSku")
    public R<Long> decrSku(@RequestParam String sku, @RequestParam String userName) {
        long result = service.decrSku(userName, sku);
        return R.okResult(AppHttpCodeEnum.SUCCESS, result);
    }

    @PostMapping("/delSku")
    public R<Long> delSku(@RequestParam String sku, @RequestParam String userName) {
        service.delSku(userName, sku);
        return R.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @PostMapping("/clear")
    public R<Long> clear(@RequestParam String userName) {
        service.clear(userName);
        return R.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
