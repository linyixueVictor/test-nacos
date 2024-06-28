package org.example.doc_order.service;

import org.example.doc_order.module.DocOrderHeader;

import java.util.List;

public interface DocOrderService {
    List<DocOrderHeader> getList(String userName, Integer status, String skuName, String keeperName);
    DocOrderHeader getInfo(Long orderNo);
    void generateOrder(String userName, String skus);
    void out(Long orderNo);
    void received(Long orderNo);
    void close(Long orderNo);
    void delete(Long orderNo);
}
