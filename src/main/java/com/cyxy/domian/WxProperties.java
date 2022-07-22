package com.cyxy.domian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "weixin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxProperties {
    private String jscode2sessionUrl;
    private String appid;
    private String appsecret;
}
