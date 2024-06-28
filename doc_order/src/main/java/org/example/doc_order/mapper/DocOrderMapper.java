package org.example.doc_order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.doc_order.module.BasSkuEntity;
import org.example.doc_order.module.DocOrderDetails;
import org.example.doc_order.module.DocOrderHeader;

import java.util.List;

@Mapper
public interface DocOrderMapper {
    List<DocOrderHeader> getList(String userName, Integer status, String skuName, String keeperName);
    List<DocOrderDetails> getDetailsListById(Long orderNo);
    DocOrderHeader getInfo(Long orderNo);
    @Select("select skuName, keeperId from bas_sku where sku = #{sku}")
    BasSkuEntity getById(String sku);
    Long getOrderNoByKeeperId(String keeperId);
    void addHeader(DocOrderHeader header);
    void addDetails(DocOrderDetails details);
    void out(Long orderNo);
    void received(Long orderNo);
    void close(Long orderNo);
    void delete(Long orderNo);
}
