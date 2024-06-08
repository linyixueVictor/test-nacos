package org.example.bas_sku.controller;

import org.example.bas_sku.service.BasSkuService;
import org.example.common.Const;
import org.example.common.CustomException;
import org.example.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sku")
public class BasSkuController {
    @Autowired
    BasSkuService basSkuService;
    @GetMapping("/getFlagById")
    public R getFlagById(@RequestParam String sku) {
        try {
            Character fifoFlag = basSkuService.getFlagById(sku);
            return R.ok(Const.SuccessMsg.BasSku.GET).put("data", fifoFlag);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
