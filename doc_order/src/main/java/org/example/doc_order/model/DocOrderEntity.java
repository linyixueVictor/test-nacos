package org.example.doc_order.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.BaseEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DocOrderEntity extends BaseEntity {
    private String orderNo;
    private String orderType;
    private List<DocOrderDetails> docOrderDetails;
}
