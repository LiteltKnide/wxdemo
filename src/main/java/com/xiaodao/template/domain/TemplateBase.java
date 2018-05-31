package com.xiaodao.template.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.Map;
import java.util.Set;

/**
 * Created by hujt49 on 2018/05/31.
 */
public class TemplateBase {

    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("template_id")
    private String templateId;

    @JsonProperty(value = "url", defaultValue = "")
    private String url;

    @JsonProperty("miniprogram")
    private WxMpTemplateMessage.MiniProgram miniProgram;

    @JsonProperty("miniprogram")
    private String miniprogram;

    @JsonProperty("data")
    private Map<String, TemplateMetaData> data;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WxMpTemplateMessage.MiniProgram getMiniProgram() {
        return miniProgram;
    }

    public void setMiniProgram(WxMpTemplateMessage.MiniProgram miniProgram) {
        this.miniProgram = miniProgram;
    }

    public Map<String, TemplateMetaData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateMetaData> data) {
        this.data = data;
    }

    public WxMpTemplateMessage toWxMpTemplateMessage() {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(this.toUser)
                .templateId(this.templateId)
                .url(this.url)
                .miniProgram(this.miniProgram)
                .build();

        Set<Map.Entry<String, TemplateMetaData>> entries =
                this.data.entrySet();
        entries.forEach(entry  -> {
            templateMessage.addWxMpTemplateData(new WxMpTemplateData(entry.getKey(), entry.getValue().getValue(), entry.getValue().getColor()));
        });

        return templateMessage;
    }
}
