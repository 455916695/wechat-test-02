package com.ax.pojo;

import org.springframework.stereotype.Component;

@Component
public class Image {
    private  String Title;  //图文标题
    private  String  Description; //图片描述
    private  String PicUrl;  //图片地址
    private  String Url;  //图文消息跳转地址

    public Image() {
    }

    public Image(String Title, String Description, String PicUrl, String Url) {
        this.Title = Title;
        this.Description = Description;
        this.PicUrl = PicUrl;
        this.Url = Url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String PicUrl) {
        this.PicUrl = PicUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }
}
