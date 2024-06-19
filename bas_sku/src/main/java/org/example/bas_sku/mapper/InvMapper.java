package org.example.bas_sku.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InvMapper {
    int descStock(String sku, Long qty);
}
