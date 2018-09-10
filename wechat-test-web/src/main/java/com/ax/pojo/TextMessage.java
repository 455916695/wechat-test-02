package com.ax.pojo;

import org.springframework.stereotype.Component;

@Component
public class TextMessage extends Message {
    private String Content;  //文本消息内容
    private  String  MsgId;   //消息id，64位整型


    public TextMessage() {

    }

    public TextMessage(String ToUserName, String FromUserName, Long CreateTime, String MsgType, String Content, String MsgId, String content1, String msgId1) {
        super(ToUserName, FromUserName, CreateTime, MsgType, Content, MsgId);
        this.Content = content1;
        this.MsgId = msgId1;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String MsgId) {
        this.MsgId = MsgId;
    }
}
