package com.yjt.springcloud.demodb.entity;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Document(collection = "T_LOGIN_LOG")
public class OperationLog {

    private Long id;

    private String module;

    private String operateType;

    private String operator;

    private String deptName;

    private String ipAdress;

    private String ipArea;

    private String state;

    private LocalDateTime operateTime;

    private String requestParam;

    private String response;

    private Long timeConsume;


}
