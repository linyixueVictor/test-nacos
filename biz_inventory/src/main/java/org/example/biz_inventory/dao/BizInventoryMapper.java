package org.example.biz_inventory.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.biz_inventory.entity.BizInventory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Mapper
public interface BizInventoryMapper {
    @Select("select * from biz_inventory")
    List<BizInventory> getList();
    @Select("select invId from biz_inventory where sku = #{sku} and qty > 0 order by inDate, produceDate")
    CopyOnWriteArrayList<Long> getFifoBySku(String sku);
    @Select("select invId from biz_inventory where sku = #{sku} and qty > 0")
    CopyOnWriteArrayList<Long> getBySku(String sku);
    @Select("select qty from biz_inventory where invId = #{invId}")
    Integer getQtyById(Long invId);
    void deduct(Long invId, int qty, Long editWho);
}
