package com.ax.pojo;

import org.springframework.stereotype.Component;

@Component
public class PhotoMessage extends Message{
    private  String Event;
    private  String EventKey;
    private  Photo  SendPicsInfo;

    public PhotoMessage( ) {

    }

    public PhotoMessage(String ToUserName, String FromUserName, Long CreateTime, String MsgType, String Content, String MsgId, String event, String eventKey, Photo sendPicsInfo) {
        super(ToUserName, FromUserName, CreateTime, MsgType, Content, MsgId);
        Event = event;
        EventKey = eventKey;
        SendPicsInfo = sendPicsInfo;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public Photo getSendPicsInfo() {
        return SendPicsInfo;
    }

    public void setSendPicsInfo(Photo sendPicsInfo) {
        SendPicsInfo = sendPicsInfo;
    }
}
