package org.example.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasSkuEntity {
    private String sku;
    private String skuName;
}
