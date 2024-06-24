package org.example.shoppingcart.model;

import lombok.Data;

@Data
public class ShoppingCart {
    private String userName;
    private String sku;
    private String skuName;
    private String qty;
}
