package com.ax.pojo;

import org.springframework.stereotype.Component;

@Component
public class ViewButton extends  Button {
    private  String url;      //网页链接，用户点击菜单可打开链接，不超过1024字节

    public ViewButton() {

    }

    public ViewButton(String type, String name, Button[] sub_button, String url) {
        super(type, name, sub_button);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
