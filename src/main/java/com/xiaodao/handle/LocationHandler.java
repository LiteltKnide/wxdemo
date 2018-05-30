package com.xiaodao.handle;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by mumut on 2017/11/14.
 */
@Component
public class LocationHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

        /**
         * for wechat test
         */
        if("gh_3c884a361561".equals(wxMessage.getToUser())){
            return createRevertTestMsg(wxMessage, "LOCATIONfrom_callback");
        }

        return null;
    }

    /**
     * StringBuffer revert = new StringBuffer();
     revert.append("<xml>");
     revert.append("<ToUserName><![CDATA[" + jsonObject.get("FromUserName") + "]]></ToUserName>");
     revert.append("<FromUserName><![CDATA[" + jsonObject.get("ToUserName") + "]]></FromUserName>");
     revert.append("<CreateTime>" + jsonObject.get("CreateTime") + "</CreateTime>");
     revert.append("<MsgType><![CDATA[text]]></MsgType>");
     revert.append("<Content><![CDATA[" + content + "]]></Content>");
     revert.append("<FuncFlag>0</FuncFlag>");
     revert.append("</xml>");
     return revert.toString();
     */
    private WxMpXmlOutMessage createRevertTestMsg(WxMpXmlMessage wxMessage, String msg){
        return WxMpXmlOutMessage.TEXT().toUser(wxMessage.getFromUser())
                                       .content(msg)
                                       .fromUser(wxMessage.getToUser())
                                       .build();
    }
}
