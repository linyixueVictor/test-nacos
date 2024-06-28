package org.example.doc_order.module;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.BaseEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DocOrderHeader extends BaseEntity {
    private Long orderNo;
    private String userName;
    private String keeperName;
    private List<DocOrderDetails> details;
}
