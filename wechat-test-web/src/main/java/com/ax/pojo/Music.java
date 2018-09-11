package com.ax.pojo;

import org.springframework.stereotype.Component;

@Component
public class Music {
    private String  Title;         //音乐标题
    private  String Description;  //音乐描述
    private  String MusicUrl;     //音乐链接
    private String HQMusicUrl;   //高质量音乐链接，WIFI环境优先使用该链接播放音乐
    private String ThumbMediaId;   //缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id

    public Music() {
    }

    public Music(String title, String description, String musicurl, String HQMusicurl, String thumbMediaId) {
        Title = title;
        Description = description;
        MusicUrl = musicurl;
        HQMusicUrl = HQMusicurl;
        ThumbMediaId = thumbMediaId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicurl) {
        MusicUrl = musicurl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
