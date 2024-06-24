package org.example.shoppingcart.service;

import org.example.shoppingcart.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> getList(String userName, String skuName);
    long incrSku(String userName, String sku);
    long decrSku(String userName, String sku);
    void delSku(String userName, String sku);
    long clear(String userName);
}
