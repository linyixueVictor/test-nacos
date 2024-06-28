package org.example.doc_order.module;

import lombok.Data;
import org.example.common.BaseEntity;

@Data
public class DocOrderDetails extends BaseEntity {
    private Long orderNo;
    private String sku;
    private String skuName;
    private Long qty;
}
