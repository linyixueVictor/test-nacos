package org.example.shoppingcart.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.shoppingcart.model.BasSkuEntity;
import org.example.shoppingcart.model.ShoppingCart;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    @Select("select sku, skuName from bas_sku where skuName like concat('%', #{skuName}, '%')")
    List<BasSkuEntity> getSkuList(String skuName);
    @Select("select skuName from bas_sku where sku = #{sku}")
    String getSkuName(String sku);
}
