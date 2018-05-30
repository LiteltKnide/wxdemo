package com.xiaodao.handle;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by mumut on 2017/11/13.
 */
@Component
public class ScanHandler extends UserRelatedHandler{

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage msg
        , Map<String, Object> context
        , WxMpService wxMpService
        , WxSessionManager sessionManager) throws WxErrorException {

        String remoteAddr = getRemoteAddr(context);

        return null;

    }
}
