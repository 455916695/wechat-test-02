package com.ax.pojo;

import org.springframework.stereotype.Component;

@Component
public class MusicMessage extends  Message {
    private Music Music;

    public com.ax.pojo.Music getMusic() {
        return Music;
    }

    public void setMusic(com.ax.pojo.Music music) {
        Music = music;
    }
}
