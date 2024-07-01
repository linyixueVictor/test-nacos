package org.example.common.module;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysOperateLog implements Serializable {
    private String status;
    private String userName;
    private String operation;
    private String message;
    private String operateTime;
}
