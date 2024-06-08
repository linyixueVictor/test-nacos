package org.example.common;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    private String addWho;
    private Date addTime;
    private String editWho;
    private Date editTime;
    public BaseEntity() {
        addTime = new Date();
    }
}
