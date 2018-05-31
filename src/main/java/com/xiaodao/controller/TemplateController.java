package com.xiaodao.controller;

import com.xiaodao.domain.Response;
import com.xiaodao.template.service.TemplateMessageSend;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hujt49 on 2018/05/31.
 */
@RestController
@RequestMapping("/template")
public class TemplateController {

    private Logger logger = LoggerFactory.getLogger(TemplateController.class);

    @Autowired
    TemplateMessageSend templateMessageSend;

    @PostMapping("/send")
    public Response sendTemplate(
            @RequestParam("templateMsg") String sendMsg
    ) {

        if (Strings.isEmpty(sendMsg) || Strings.isBlank(sendMsg)) {
            return Response.fail("params can not be null");
        }

        int i = templateMessageSend.sendTemplate(sendMsg);
        if (i == 1) {
            return Response.ok("消息发送成功", "");
        } else {
            return Response.fail("失败");
        }
    }
}
