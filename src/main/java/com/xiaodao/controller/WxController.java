package com.xiaodao.controller;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
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
    @Autowired
    WxMpConfigStorage wxMpConfigStorage;
    @Autowired
    WxMpMessageRouter wxMpMessageRouter;

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

    @PostMapping(value = { "/{wxname:wx\\w+|weixin\\w+}"}, produces = "application/xml; charset=UTF-8")
    protected void doMessage(@PathVariable("wxname") String wxname,
                             @RequestParam(name = "signature",
                                     required = false) String signature,
                             @RequestParam(name = "timestamp",
                                     required = false) String timestamp,
                             @RequestParam(name = "nonce", required = false) String nonce,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        logger.info("POST from wxid {}", wxname);
        logger.info("RemoteAddr: " + request.getRemoteAddr());
        logger.info("QueryString: " + request.getQueryString());
        logger.info("request URI:" + request.getRequestURI());
        logger.info("request UserAgent:" + request.getHeader("User-Agent"));
        logger.info("request Server:" + request.getLocalAddr());
        String echostr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            response.getWriter().println(echostr);
            return;
        }

        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
                "raw" :
                request.getParameter("encrypt_type");

        WxMpXmlMessage inMessage = null;

        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
        } else if ("aes".equals(encryptType)) {
            // 是aes加密的消息
            String msgSignature = request.getParameter("msg_signature");
            inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
        } else {
            response.getWriter().println("不可识别的加密类型");
            return;
        }

        WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);

        if (outMessage != null) {
            if ("raw".equals(encryptType)) {
                response.getWriter().write(outMessage.toXml());
            } else if ("aes".equals(encryptType)) {
                response.getWriter().write(outMessage.toEncryptedXml(wxMpConfigStorage));
            }
            return;
        }
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
