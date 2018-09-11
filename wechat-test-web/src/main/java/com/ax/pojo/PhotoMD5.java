package com.ax.pojo;

import org.springframework.stereotype.Component;

@Component
public class PhotoMD5 {
    private  String PicMd5Sum;

    public PhotoMD5() {
    }

    public PhotoMD5(String picMd5Sum) {
        PicMd5Sum = picMd5Sum;
    }

    public String getPicMd5Sum() {
        return PicMd5Sum;
    }

    public void setPicMd5Sum(String picMd5Sum) {
        PicMd5Sum = picMd5Sum;
    }
}
