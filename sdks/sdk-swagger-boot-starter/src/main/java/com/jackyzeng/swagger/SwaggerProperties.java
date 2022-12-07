package com.jackyzeng.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "platform.swagger")
public class SwaggerProperties {

    private boolean enabled;

    private String title;

    private String description;

}
