package org.example.doc_order.service;

import org.example.doc_order.module.DocOrderHeader;

import java.util.List;

public interface DocOrderService {
    List<DocOrderHeader> getList(String userName, Integer status, String skuName, String keeperName);
    DocOrderHeader getInfo(Long orderNo, String userName);
    void generateOrder(String userName, String skus);
    void out(Long orderNo, String userName);
    void received(Long orderNo, String userName);
    void close(Long orderNo, String userName);
    void delete(Long orderNo, String userName);
}
