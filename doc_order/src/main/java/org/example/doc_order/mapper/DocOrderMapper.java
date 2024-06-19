package org.example.doc_order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.doc_order.model.DocOrderDetails;
import org.example.doc_order.model.DocOrderEntity;

import java.util.List;

@Mapper
public interface DocOrderMapper {
    @Select("select * from doc_order_header where orderNo = #{orderNo}")
    DocOrderEntity getOrderInfo(String orderNo);
    List<DocOrderDetails> getOrderDetailsList(String orderNo, String sku);
    void addHeader(DocOrderEntity entity);
    void addDetails(DocOrderDetails entity);
    int updateHeaderFirst(String orderNo, String outWho);
    int updateHeaderLast(String orderNo, String outWho);
    int updateDetailsStatus(String orderNo, String sku, String outWho);
    int getCreateStatusDetailsCount(String orderNo);
}
