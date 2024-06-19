package org.example.doc_order.model;

import lombok.Data;
import org.example.common.BaseEntity;

@Data
public class DocOrderDetails extends BaseEntity {
    private String orderNo;
    private String sku;
    private Integer qty;
}
