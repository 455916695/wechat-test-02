package com.ax.pojo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Photo {
     private  int Count;
     private  List PicList;

    public Photo() {
    }

    public Photo(int count, List picList) {
        Count = count;
        PicList = picList;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public List getPicList() {
        return PicList;
    }

    public void setPicList(List picList) {
        PicList = picList;
    }
}
