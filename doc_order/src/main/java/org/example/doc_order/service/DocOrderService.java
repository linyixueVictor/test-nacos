package org.example.doc_order.service;

import org.example.doc_order.model.DocOrderDetails;
import org.example.doc_order.model.DocOrderEntity;

public interface DocOrderService {
    DocOrderEntity getInfo(String orderNo);
    void addHeader(DocOrderEntity entity);
    void addDetails(DocOrderDetails entity);
    void ship(String shipType, String orderNo, String sku, String outWho);
}
