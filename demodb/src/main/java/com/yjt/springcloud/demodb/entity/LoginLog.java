package com.yjt.springcloud.demodb.entity;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Document(collection = "T_LOGIN_LOG")
public class LoginLog extends BaseEnity {

        private Long id;

        private String userName;

        private String ipAdress;

        private String ipArea;

        private String browser;

        private String loginState;

        private LocalDateTime loginDateTime;

        private String operate;


}
