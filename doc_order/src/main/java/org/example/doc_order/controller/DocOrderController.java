package org.example.doc_order.controller;

import org.example.common.consts.AppHttpCodeEnum;
import org.example.common.R;
import org.example.doc_order.module.DocOrderHeader;
import org.example.doc_order.service.DocOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class DocOrderController {
    @Autowired
    DocOrderService service;
    @GetMapping("/getList")
    public R<List<DocOrderHeader>> getList(@RequestParam String userName,
                                           @RequestParam(required = false) Integer status,
                                           @RequestParam(required = false) String skuName,
                                           @RequestParam(required = false) String keeperName) {
        List<DocOrderHeader> list = service.getList(userName, status, skuName, keeperName);
        return R.okResult(AppHttpCodeEnum.SUCCESS_GET, list);
    }

    @GetMapping("/getInfo")
    public R<DocOrderHeader> getInfo(@RequestParam Long orderNo, @RequestParam String userName) {
        DocOrderHeader header = service.getInfo(orderNo, userName);
        return R.okResult(AppHttpCodeEnum.SUCCESS_GET, header);
    }

    @PostMapping("/generateOrder")
    public R<Long> generateOrder(@RequestParam String userName,@RequestParam String skus) {
        service.generateOrder(userName, skus);
        return R.okResult(AppHttpCodeEnum.SUCCESS_GENERATE);
    }

    @PostMapping("/out")
    public R<Long> out(@RequestParam Long orderNo, @RequestParam String userName) {
        service.out(orderNo, userName);
        return R.okResult(AppHttpCodeEnum.SUCCESS_OUT);
    }

    @PostMapping("/received")
    public R<Long> received(@RequestParam Long orderNo, @RequestParam String userName) {
        service.received(orderNo, userName);
        return R.okResult(AppHttpCodeEnum.SUCCESS_RECEIVED);
    }

    @PostMapping("/close")
    public R<Long> close(@RequestParam Long orderNo, @RequestParam String userName) {
        service.close(orderNo, userName);
        return R.okResult(AppHttpCodeEnum.SUCCESS_CLOSE);
    }

    @PostMapping("/delete")
    public R<Long> delete(@RequestParam Long orderNo, @RequestParam String userName) {
        service.delete(orderNo, userName);
        return R.okResult(AppHttpCodeEnum.SUCCESS_DELETE);
    }
}
