package com.xiaodao.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hujt49 on 2018/05/17.
 */
@Configuration
@EnableConfigurationProperties(WechatSetting.class)
public class WechatConfig {

    @Autowired
    private WechatSetting wechatSetting;

    @Bean
    public WxMpService wxMpService(WxMpConfigStorage wxMpConfigStorage){
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAesKey(wechatSetting.getMpAesKey());
        wxMpInMemoryConfigStorage.setAppId(wechatSetting.getMpAppId());
        wxMpInMemoryConfigStorage.setSecret(wechatSetting.getMpAppSecret());
        wxMpInMemoryConfigStorage.setToken(wechatSetting.getMpToken());
        return wxMpInMemoryConfigStorage;
    }
}
