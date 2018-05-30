package com.xiaodao.handle;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by changgeng on 2017/11/30.
 */
@Component
public class ClickHandler extends UserRelatedHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        if (wxMpXmlMessage.getEventKey().equals("HOWEINV")) {
           return createRevertScanInvText(wxMpXmlMessage, "电子发票的开票方式因商家而异，具体请向商家咨询。如在开票过程中遇到问题，请随时咨询微信客服，我们将竭诚为您服务。");
        } else if (wxMpXmlMessage.getEventKey().equals("TAIKWME")) {
            return createRevertScanInvText(wxMpXmlMessage, "请回复您遇到的问题关键词，智能客服将尽快为您解答。");
        } else if (wxMpXmlMessage.getEventKey().equals("FINDQRC")) {
            return createRevertPic(wxMpXmlMessage, "OEPRgxOhhYtvjM78Fz2cXRe5JfyerqqHbOo9bbGXJ1k");
        }
       return null;
    }
}
