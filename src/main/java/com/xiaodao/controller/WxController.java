package com.xiaodao.controller;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hujt49 on 2018/05/17.
 */
@RequestMapping("/wechat")
@RestController
public class WxController {

    private Logger logger = LoggerFactory.getLogger(WxController.class);

    @Autowired
    WxMpService wxMpService;

    @RequestMapping("/test")
    public String test() {
        try {
            return wxMpService.getAccessToken();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping(value = { "/{wxname:wx\\w+|weixin\\w+}"}, produces = "text/plain;charset=utf-8")
    public String connect(@PathVariable("wxname") String wxname,
                          @RequestParam(name = "signature",
                                  required = false) String signature,
                          @RequestParam(name = "timestamp",
                                  required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr,
                          HttpServletRequest request, HttpServletResponse response){
        logger.info("GET");
        logger.info("RemoteAddr: " + request.getRemoteAddr());
        logger.info("QueryString: " + request.getQueryString());
        logger.info("request URI:" + request.getRequestURI());
        logger.info("request UserAgent:" + request.getHeader("User-Agent"));
        logger.info("request Server:" + request.getLocalAddr());
        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

    @RequestMapping("/sendText")
    public void sendTextMessage() {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("oiFO803DJu3Qqk5jafFFxOYOChKs")
                .templateId("Xn4Vm_fCWlY7f43-OUOWehAGnq4wa8WxPsWRuNDhpOo")
                .url("www.baidu.com")
                .build();

        templateMessage.getData().add(new WxMpTemplateData("AA", "haha", "FFFFFF"));
        templateMessage.getData().add(new WxMpTemplateData("BB", "BIBI", "CCC"));

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
