package com.ax.test;

import com.ax.pojo.AccessToken;
import com.ax.utils.WeChatUtil;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class WeChatTest {

//    @Test    //测试失败，文件上传  不能获取
//    public void  getAccessTokenTest() throws IOException {
//        AccessToken accessToken = WeChatUtil.getAccessToken();
//        System.out.println("Access_Token为:"+accessToken.getToken());
//        System.out.println("有效时间为:"+accessToken.getExpiresIn());
//
//        String path = "D:/File/image.png";
//        String MediaId = WeChatUtil.getResponseMess(path,accessToken.getToken(),"image");
//    }

//    @Test   //测试成功
//    public void  creatMeanTest () throws IOException {
//        AccessToken accessToken = WeChatUtil.getAccessToken();
//
//        String menu = JSONObject.fromObject(WeChatUtil.initMenu()).toString();
//
//        System.out.println(menu);
//        int result = WeChatUtil.createMenu(accessToken.getToken(),menu);
//
//        System.out.println(result);
//        if(result == 0) {
//            System.out.println("菜单创建成功");
//        }else{
//            System.out.println("菜单创建失败");
//        }
//    }

}
