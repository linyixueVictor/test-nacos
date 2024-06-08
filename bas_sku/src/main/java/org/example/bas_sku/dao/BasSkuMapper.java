package org.example.bas_sku.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BasSkuMapper {
    @Select("select fifoFlag from bas_sku where sku = #{sku}")
    Character getFlagById(String sku);
}
