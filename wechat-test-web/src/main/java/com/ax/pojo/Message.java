package com.ax.pojo;

import org.springframework.stereotype.Component;



public abstract class Message  {
    private String  ToUserName;  //开发者微信号
    private String FromUserName; //发送方账号
    private  Long  CreateTime;  //消息创建时间（整型）
    private String  MsgType;  //text


    public Message() {
    }

    public Message(String ToUserName, String FromUserName, Long  CreateTime, String MsgType, String Content, String MsgId) {
        this.ToUserName = ToUserName;
        this.FromUserName = FromUserName;
        this.CreateTime = CreateTime;
        this.MsgType = MsgType;

    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String ToUserName) {
        this.ToUserName = ToUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String FromUserName) {
        this.FromUserName = FromUserName;
    }

    public Long  getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long  CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String MsgType) {
        this.MsgType = MsgType;
    }


}
