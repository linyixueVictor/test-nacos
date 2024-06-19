package org.example.doc_order.feign;

import org.example.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "biz-inventory", path = "/inv")
public interface BizInventoryFeign {
    @PostMapping("/deduct")
    R deduct(@RequestParam String sku, @RequestParam Integer qty, @RequestParam String editWho);
}
