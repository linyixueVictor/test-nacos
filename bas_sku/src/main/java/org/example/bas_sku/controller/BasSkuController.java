package org.example.bas_sku.controller;

import org.example.bas_sku.model.BasSkuEntity;
import org.example.bas_sku.service.BasSkuService;
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
        try {
            BasSkuEntity entity = basSkuService.getById(sku);
            return R.ok(Const.SuccessMsg.BasSku.GET, entity);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
