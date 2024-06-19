package org.example.doc_order.controller;

import org.example.common.Const;
import org.example.common.R;
import org.example.common.exception.CustomException;
import org.example.doc_order.model.DocOrderDetails;
import org.example.doc_order.model.DocOrderEntity;
import org.example.doc_order.service.DocOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class DocOrderController {
    @Autowired
    DocOrderService service;
    @GetMapping("/getInfo")
    public R getInfo(@RequestParam String orderNo) {
        try {
            DocOrderEntity entity = service.getInfo(orderNo);
            return R.ok(Const.SuccessMsg.DocOrder.GET, entity);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @PostMapping("/addHeader")
    public R addHeader(@RequestBody DocOrderEntity entity) {
        try {
            service.addHeader(entity);
            return R.ok(Const.SuccessMsg.DocOrder.ADD);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @PostMapping("/addDetails")
    public R addDetails(@RequestBody DocOrderDetails entity) {
        try {
            service.addDetails(entity);
            return R.ok(Const.SuccessMsg.DocOrder.ADD);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @PostMapping("/ship")
    public R ship(@RequestParam String shipType, @RequestParam String orderNo,
                  @RequestParam(required = false) String sku, @RequestParam String outWho) {
        try {
            service.ship(shipType, orderNo, sku, outWho);
            return R.ok(Const.SuccessMsg.DocOrder.SHIP);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
