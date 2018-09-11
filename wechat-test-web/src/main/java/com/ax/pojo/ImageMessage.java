package com.ax.pojo;

import org.springframework.stereotype.Component;

@Component
public class ImageMessage extends Message {

    private  Image Image;


    public  Image getImage() {
        return Image;
    }

    public void setImage( Image image) {
        Image = image;
    }
}
