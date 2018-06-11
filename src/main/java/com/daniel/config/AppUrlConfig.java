package com.daniel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Daniel on 2018/6/10.
 *
 * 微信公众号配置
 */
@Component
@ConfigurationProperties(prefix = "appUrl")
@Data
public class AppUrlConfig {

    /**
     * 微信公众平台授权url
     */
    public String weChatMpAuthorize;

    /**
     * 微信开放平台授权url
     */
    public String weChatOpenAuthorize;

    /**
     * 点餐系统
     */
    public String sell;
}
