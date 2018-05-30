package com.xiaodao.config;

import com.xiaodao.handle.*;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hujt49 on 2018/05/30.
 */
@Configuration
public class WechatMpRouterConfiguration {

    @Autowired
    TextHandler textHandler;
    @Autowired
    LogHandler logHandler;
    @Autowired
    SubscribeHandler subscribeHandler;
    @Autowired
    ViewHandler viewHandler;
    @Autowired
    ScanMsgHandler scanMsgHandler;
    @Autowired
    LocationHandler locationHandler;
    @Autowired
    ScanHandler scanHandler;
    @Autowired
    ClickHandler clickHandler;
    @Autowired
    ImageHandler imageHandler;

    @Bean
    public WxMpMessageRouter router(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);


        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(logHandler).next();

        // 关注事件
        newRouter.rule().async(false).msgType(WxConsts.EventType.SUBSCRIBE)
                .handler(subscribeHandler)
                .end();

        newRouter.rule().async(false).msgType(WxConsts.EventType.VIEW)
                .handler(viewHandler)
                .end();

        newRouter.rule().async(false).msgType(WxConsts.EventType.SCANCODE_WAITMSG)
                .handler(scanMsgHandler)
                .end();

//        // 取消关注事件
        // no action
//        newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
//            .event(WxConsts.EVT_UNSUBSCRIBE)
//            .handler(this.getUnsubscribeHandler()).end();

        // 上报地理位置事件
        newRouter.rule().async(false).msgType(WxConsts.EventType.LOCATION)
                .handler(locationHandler)
                .end();

//        // 接收地理位置消息
//        newRouter.rule().async(false).msgType(WxConsts.XML_MSG_LOCATION)
//            .handler(this.getLocationHandler()).end();

        // 扫码事件
        newRouter.rule().async(false).msgType(WxConsts.EventType.SCAN)
                .handler(scanHandler).end();

        // 点击事件
        newRouter.rule().async(false).msgType(WxConsts.EventType.CLICK)
                .handler(clickHandler).end();

        // 文字消息
        newRouter.rule().async(false).msgType(WxConsts.MassMsgType.TEXT)
                .handler(textHandler).end();

        // 图片消息
        newRouter.rule().async(false).msgType(WxConsts.MediaFileType.IMAGE)
                .handler(imageHandler).end();

        // 默认
//        newRouter.rule().async(false).handler(this.getMsgHandler()).end();

        return newRouter;
    }
}
