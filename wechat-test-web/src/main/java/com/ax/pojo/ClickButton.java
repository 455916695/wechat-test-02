package com.ax.pojo;

import org.springframework.stereotype.Component;

@Component
public class ClickButton extends Button {
    private  String  key;

    public ClickButton() {

    }

    public ClickButton(String type, String name, Button[] sub_button, String key) {
        super(type, name, sub_button);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

