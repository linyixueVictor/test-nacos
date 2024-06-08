package org.example.biz_inventory.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.example.biz_inventory.entity.BizInventory;
import org.example.biz_inventory.sentinel.SentinelException;
import org.example.biz_inventory.service.BizInventoryService;
import org.example.common.Const;
import org.example.common.CustomException;
import org.example.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inv")
public class BizInventoryController {
    @Autowired
    BizInventoryService bizInventoryService;
    @GetMapping("/getList")
    public R getList() {
        try {
            List<BizInventory> list = bizInventoryService.getList();
            return R.ok(Const.SuccessMsg.BizInventory.GET).put("data", list);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 扣减库存
     * @return
     */
    @PostMapping("/deduct")
    /*@SentinelResource(value = "deduct",
            blockHandler = "handleException", blockHandlerClass = SentinelException.class,
            fallback = "fallbackException", fallbackClass = SentinelException.class)*/
    public R deduct(@RequestParam String sku, @RequestParam Integer qty, @RequestParam Long editWho) {
        try {
            bizInventoryService.deduct(sku, qty, editWho);
            return R.ok(Const.SuccessMsg.BizInventory.DEDUCT);
        } catch (CustomException e) {
            e.printStackTrace();
            return R.fail(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
