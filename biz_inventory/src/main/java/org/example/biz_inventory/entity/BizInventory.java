package org.example.biz_inventory.entity;

import lombok.Data;
import org.example.common.BaseEntity;

@Data
public class BizInventory extends BaseEntity {
    private Long invId;
    private String sku;
    private Integer qty;
}
