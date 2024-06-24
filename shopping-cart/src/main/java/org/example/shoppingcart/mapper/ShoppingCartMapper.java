package org.example.shoppingcart.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.shoppingcart.model.ShoppingCart;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    List<ShoppingCart> getList(String userName, String skuName);
    void addSku(String userName, String sku);
    void updateQty(String userName, String sku, long qty);
    void deleteSku(String userName, String sku);
}
