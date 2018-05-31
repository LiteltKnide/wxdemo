package com.xiaodao.template.domain;

/**
 * Created by hujt49 on 2018/05/31.
 */
public class TemplateMetaData {

    private String value;

    private String color;

    public TemplateMetaData(){}

    public TemplateMetaData(String value) {
        this.value = value;
        this.color = "#173177";
    }

    public TemplateMetaData(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
