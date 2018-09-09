package com.ax.web;

import com.ax.pojo.Message;
import com.ax.utils.CheckUtil;
import com.ax.utils.MessageUtil;
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

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ResponseBody         // 微信加密签名           时间戳      随机数       随机字符串
    public String check(@RequestParam(defaultValue = "") String signature, @RequestParam(defaultValue = "") String timestamp, @RequestParam(defaultValue = "") String nonce, @RequestParam(defaultValue = "") String echostr) {
        //signature    结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return "";
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    @ResponseBody         // 微信加密签名           时间戳      随机数       随机字符串
    public String check(HttpServletRequest request) {
        String s = "";  // 接收返回的xml文件
        try {
            //将request请求，传到Message工具类的转换方法中，返回接收到的Map对象
            Map<String, String> map = MessageUtil.xmlToMap(request);

            //从集合中，获取XML各个节点的内容
            String ToUserName = map.get("ToUserName");  //开发者微信号
            String FromUserName = map.get("FromUserName");  //发送方账号
            String CreateTime = map.get("CreateTime"); //消息创建时间（整型）
            String MsgType = map.get("MsgType");  //text 消息类型
            String Content = map.get("Content"); //文本消息内容
            String MsgId = map.get("MsgId");     //消息id，64位整型

            if (MsgType.equals(MessageUtil.MESSAGE_TEXT)) { //判断消息类型是否是文本消息（text）
                if ("1".equals(Content)) {
                    s = MessageUtil.initMessage(ToUserName, FromUserName, "果真是同道中人！！！");
                } else if ("2".equals(Content)) {
                    s = MessageUtil.initMessage(ToUserName, FromUserName, "老夫掐指一算，你的开发时间应该不长！！！");
                }else if("?".equals(Content) || "？".equals(Content)) {
                    s = MessageUtil.initMessage(ToUserName, FromUserName, MessageUtil.menuMessage());
                }else{
                    s = MessageUtil.initMessage(ToUserName, FromUserName,"没让选的别瞎选！！！");
                }
//                Message message = new Message();
//                message.setToUserName(FromUserName);  //用户名
//                message.setFromUserName(ToUserName);  //openId
//                message.setMsgType("text");        //类型
//                message.setCreateTime(new Date().getTime( ));
//                message.setContent("您好! "+FromUserName+"\n我是:"+ToUserName+"\n"
//                    +"\n您发送的消息类型为："+MsgType
//                    +"\n您发送的时间为："+CreateTime
//                        +"\n我回复的时间为:"+message.getCreateTime()
//                        +"\n您发送的内容是:"+Content
//                        +"\n你再发一句试试！！！"
//                );
//                 s = MessageUtil.objectToXml(message);

            } else if (MsgType.equals(MessageUtil.MESSAGE_EVENT)) {  //判断是否是事件
                String eventType = map.get("Event");  //获取具体事件类型
                if (eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)) {  //判断是否为关注事件
                    //如果是关注事件，就给客户响应一个主菜单消息
                    s = MessageUtil.initMessage(ToUserName, FromUserName, MessageUtil.menuMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

}
