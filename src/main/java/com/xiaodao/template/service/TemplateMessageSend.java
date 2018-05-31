package com.xiaodao.template.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaodao.template.domain.TemplateBase;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by hujt49 on 2018/05/30.
 */
@Component
public class TemplateMessageSend {

    @Autowired
    WxMpService wxMpService;

    public void testTemplateSend(Map map) {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(map.get("toUser")+"")
                .templateId("")
                .url("")
                .build();

        templateMessage.getData().add(new WxMpTemplateData(map.get("name1")+"", map.get("value1")+"", map.get("color1")+""));
        templateMessage.getData().add(new WxMpTemplateData(map.get("name2")+"", map.get("value2")+"", map.get("color2")+""));

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }


    public int sendTemplate(String message) {

        int rnt = -1;
        try {
            TemplateBase base = new ObjectMapper().readValue(message, TemplateBase.class);
            WxMpTemplateMessage wxMpTemplateMessage = base.toWxMpTemplateMessage();
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
            rnt = 1;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return rnt;
    }
}
