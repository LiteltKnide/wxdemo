package com.xiaodao.handle;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.ScanCodeInfo;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by mumut on 2017/11/14.
 */
@Component
public class ScanMsgHandler extends UserRelatedHandler {


    static Logger logger = LoggerFactory.getLogger(ScanMsgHandler.class);
    
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        String remoteAddr = getRemoteAddr(context);
//        JSONObject ScanCodeInfo = JSONObject.fromObject(jsonObject.getString("ScanCodeInfo"));
        ScanCodeInfo scanCodeInfo = wxMessage.getScanCodeInfo();
        logger.debug("ScanCodeInfo: " + scanCodeInfo);

        return null;
    }
}
