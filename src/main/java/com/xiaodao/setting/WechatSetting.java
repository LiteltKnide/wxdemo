package com.xiaodao.setting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by hujt49 on 2018/05/17.
 */
@Component
@ConfigurationProperties("wechat")
public class WechatSetting {

    private String mpAppId;
    private String mpAppSecret;
    private String mpToken;
    private String mpAesKey;

    public String getMpAppId() {
        return mpAppId;
    }

    public void setMpAppId(String mpAppId) {
        this.mpAppId = mpAppId;
    }

    public String getMpAppSecret() {
        return mpAppSecret;
    }

    public void setMpAppSecret(String mpAppSecret) {
        this.mpAppSecret = mpAppSecret;
    }

    public String getMpToken() {
        return mpToken;
    }

    public void setMpToken(String mpToken) {
        this.mpToken = mpToken;
    }

    public String getMpAesKey() {
        return mpAesKey;
    }

    public void setMpAesKey(String mpAesKey) {
        this.mpAesKey = mpAesKey;
    }
}
