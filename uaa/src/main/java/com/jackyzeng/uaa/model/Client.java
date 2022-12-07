package com.jackyzeng.uaa.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oauth_client_details")
public class Client {
    private String clientId;
    private String clientName;
    private String clientSecret;
}
