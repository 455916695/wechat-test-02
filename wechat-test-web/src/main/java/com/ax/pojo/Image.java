package com.ax.pojo;

import org.springframework.stereotype.Component;

@Component
public class Image {
    private String MediaId;

    public Image() {
    }

    public Image(String mediaId) {
        MediaId = mediaId;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
