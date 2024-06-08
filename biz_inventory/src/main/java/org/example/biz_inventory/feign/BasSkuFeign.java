package org.example.biz_inventory.feign;

import org.example.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bas-sku", path = "/sku")
public interface BasSkuFeign {
    @GetMapping("/getFlagById")
    R getFlagById(@RequestParam String sku);

}
