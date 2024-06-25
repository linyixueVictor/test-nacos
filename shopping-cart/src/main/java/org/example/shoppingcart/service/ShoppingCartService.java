package org.example.shoppingcart.service;

import org.example.shoppingcart.model.ShoppingCart;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService {
    Map getList(String userName, String skuName);
    long incrSku(String userName, String sku);
    long decrSku(String userName, String sku);
    void delSku(String userName, String sku);
    void clear(String userName);
}
