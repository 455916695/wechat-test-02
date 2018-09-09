package com.ax.web;

import com.ax.pojo.Message;
import com.ax.utils.CheckUtil;
import com.ax.utils.Messageutil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
public class WeChatController {

    @RequestMapping(value = "/check",method = RequestMethod.GET)
    @ResponseBody         // 微信加密签名           时间戳      随机数       随机字符串
    public String check(@RequestParam(defaultValue = "") String signature, @RequestParam(defaultValue = "")String timestamp,@RequestParam(defaultValue = "") String nonce, @RequestParam(defaultValue = "")String echostr ){
        //signature    结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
        if(CheckUtil.checkSignature(signature,timestamp,nonce)) {
            return echostr;
        }
        return "";
    }
    @RequestMapping(value = "/check",method = RequestMethod.POST,produces= MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody         // 微信加密签名           时间戳      随机数       随机字符串
    public String check(HttpServletRequest request){
        try {
            //将request请求，传到Message工具类的转换方法中，返回接收到的Map对象
            Map<String,String > map = Messageutil.xmlToMap(request);

            //从集合中，获取XML各个节点的内容
            String  ToUserName = map.get("ToUserName");
            String  FromUserName = map.get("FromUserName");
            String  CreateTime = map.get("CreateTime");
            String  MsgType = map.get("MsgType");
            String  Content = map.get("Content");
            String  MsgId = map.get("MsgId");

            if(MsgType.equals("text")) { //判断消息类型是否是文本消息（text）
                Message message = new Message();
                message.setToUserName(FromUserName);  //用户名
                message.setFromUserName(ToUserName);  //openId
                message.setMsgType("text");        //类型
                message.setCreateTime(new Date().getTime( ));
                message.setContent("您好! "+FromUserName+"\n我是:"+ToUserName+"\n"
                    +"\n您发送的消息类型为："+MsgType
                    +"\n您发送的时间为："+CreateTime
                        +"\n我回复的时间为:"+message.getCreateTime()
                        +"\n您发送的内容是:"+Content
                        +"\n你再发一句试试！！！"
                );
                String s = Messageutil.objectToXml(message);
                return s;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

}
