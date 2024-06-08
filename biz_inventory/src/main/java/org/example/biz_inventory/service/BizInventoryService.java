package org.example.biz_inventory.service;

import org.example.biz_inventory.entity.BizInventory;

import java.util.List;

public interface BizInventoryService {
    List<BizInventory> getList();
    void deduct(String sku, Integer qty, Long editWho);
}
