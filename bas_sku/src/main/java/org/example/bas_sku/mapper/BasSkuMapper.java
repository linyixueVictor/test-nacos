package org.example.bas_sku.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.bas_sku.model.BasSkuEntity;

@Mapper
public interface BasSkuMapper {
    @Select("select sku from bas_sku where sku = #{sku}")
    BasSkuEntity getById(String sku);
}
