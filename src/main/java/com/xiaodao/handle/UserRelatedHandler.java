package com.xiaodao.handle;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.builder.outxml.NewsBuilder;

import java.util.Map;

/**
 * Created by mumut on 2017/11/14.
 */
public abstract class UserRelatedHandler extends AbstractHandler {


    /**
     * get RemoteAddr from the context
     * @param context
     * @return
     */
    protected String getRemoteAddr(Map<String, Object> context){
        return (String)context.getOrDefault("remoteAddr", "");
    }



    protected WxMpXmlOutMessage createRevertPicText(WxMpXmlMessage msg, String scanStr, String apikey) {
        String[] scaninfo = scanStr.split(",");
        if(scanStr.contains("##")){
            scaninfo=scanStr.split("##");
        }
        NewsBuilder nb = WxMpXmlOutMessage.NEWS()
            .fromUser(msg.getToUser())
            .toUser(msg.getFromUser());

        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();


        /**
         * 增加文章
         */
        return nb.addArticle(item).build();
    }


    /**
     * StringBuffer revert = new StringBuffer();
     revert.append("<xml>");
     revert.append("<ToUserName><![CDATA[" + jsonObject.get("FromUserName") + "]]></ToUserName>");
     revert.append("<FromUserName><![CDATA[" + jsonObject.get("ToUserName") + "]]></FromUserName>");
     revert.append("<CreateTime>" + jsonObject.get("CreateTime") + "</CreateTime>");
     revert.append("<MsgType><![CDATA[text]]></MsgType>");
     revert.append("<Content><![CDATA[" + msg + "]]></Content>");
     revert.append("</xml>");
     return revert.toString();
     * @return
     */
    protected WxMpXmlOutMessage createRevertSimpleInfoText(WxMpXmlMessage wxMsg, String msg){
        /**
         * it seems no difference between two method since the <FuncFlag>0</FuncFlag> has been deprecated
         */
        return  createRevertScanInvText(wxMsg, msg);
    }

    protected WxMpXmlOutMessage createRevertScanInvText(WxMpXmlMessage wxMsg, String msg) {
        /**
         * funcflag has been deprecated
         */
        return WxMpXmlOutMessage.TEXT()
            .toUser(wxMsg.getFromUser())
            .fromUser(wxMsg.getToUser())
            .content(msg).build();
    }

}
