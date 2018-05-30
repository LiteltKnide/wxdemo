package com.xiaodao.handle;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by mumut on 2017/11/10.
 */
@Component
public class SubscribeHandler extends UserRelatedHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());
        try {
            String remoteAddr = getRemoteAddr(context);
            return handleSpecial(wxMessage, remoteAddr);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage, String remoteAddr)
        throws Exception {
        // if(wxMessage.getEventKey().contains("qrscene_")){
        //     String apikey = updateUserApiKey(wxMessage.getFromUser(), remoteAddr, wxMessage.getToUser());
        //     WxMpXmlOutMessage outMessage = createRevertPicText(wxMessage, wxMessage.getEventKey().substring(8), apikey);// 创建XML
        //     logger.info("PicText ResponseStr: {}", outMessage.toXml());
        //     return outMessage;
        // }else{
        //     return createRevertWelcomeText(wxMessage);
        // }
        return null;
    }






    private Long ipToLong(String ipAddress){
        long result = 0;
        String[] ipAddressInArray = ipAddress.split("\\.");
        for (int i = 3; i >= 0; i--) {
            long ip = Long.parseLong(ipAddressInArray[3 - i]);
            result |= ip << (i * 8);
        }
        return result;
    }
}