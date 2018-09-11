package com.ax.web;

import com.ax.pojo.AccessToken;
import com.ax.pojo.Message;
import com.ax.pojo.MusicMessage;
import com.ax.utils.CheckUtil;
import com.ax.utils.MessageUtil;
import com.ax.utils.WeChatUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

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
//            Set<String> strings = map.keySet();
//            for (String key : strings) {
//                String s1 = map.get(key);
//                System.out.println(key +"==="+s1+ "\n");
//            }

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
                    }else if("?".equals(Content) || "？".equals(Content)|| "菜单".equals(Content) || "功能".equals(Content)) {
                        s = MessageUtil.initMessage(ToUserName, FromUserName, MessageUtil.menuMessage());
                    } else if("3".equals(Content)) {
                        s = MessageUtil.initNewsMessage(ToUserName,FromUserName);
                    } else if ("4".equals(Content)) {
                        s = MessageUtil.initImageMessage(ToUserName,FromUserName);
                    }else if("5".equals(Content)) {
                        s = MessageUtil.initMusicMessage(ToUserName,FromUserName);
                    } else {
                        s = MessageUtil.initMessage(ToUserName, FromUserName,"没让选的别瞎选！！！");
                    }

            } else if (MsgType.equals(MessageUtil.MESSAGE_EVENT)) {  //判断是否是事件
                    String eventType = map.get("Event");  //获取具体事件类型
                    if (eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)) {  //判断是否为关注事件
                        //如果是关注事件，就给客户响应一个主菜单消息
                        s = MessageUtil.initMessage(ToUserName, FromUserName, MessageUtil.menuMessage());
                    }else if(eventType.equals(MessageUtil.MESSAGE_CLICK)){
                        String key = map.get("EventKey");  //获取click 类型按钮的 key
                        if ("1".equals(key)) {
                            s = MessageUtil.initMessage(ToUserName, FromUserName, "果真是同道中人！！！");
                        } else if ("2".equals(key)) {
                            s = MessageUtil.initMessage(ToUserName, FromUserName, "老夫掐指一算，你的开发时间应该不长！！！");
                        }else if("?".equals(key)) {
                            s = MessageUtil.initMessage(ToUserName, FromUserName, MessageUtil.menuMessage());
                        } else if("3".equals(key)) {
                            s = MessageUtil.initNewsMessage(ToUserName,FromUserName);
                        } else if ("4".equals(key)) {
                            s = MessageUtil.initImageMessage(ToUserName,FromUserName);
                        }else if("5".equals(key)) {
                            s = MessageUtil.initMusicMessage(ToUserName,FromUserName);
                        }
                   } else if("pic_sysphoto".equals(eventType)) {
                        String key = map.get("EventKey");  //获取click 类型按钮的 key
                            if("9".equals(key)) {
                                    int random = (int)Math.random() * 100;
                                    if(random < 60) {
                                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+";"+"废材！！！长相丑陋！！根骨奇差！！");
                                    }else if(random >=60 && random <70) {
                                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+";"+"中等！！！长相勉强！！可以练练，强身健体！！");
                                    }else if (random >= 70 && random <80) {
                                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+";"+"上等！！！长相清秀！！可以练功夫！！");
                                    } else  if( random >=80 && random < 90) {
                                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+"v"+"上上等！！！出生就是优势！！可以练任何功夫！！");
                                    } else if (random >=90 && random <99) {
                                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+";"+"优秀！！！已经好的无法评价了！！");
                                    } else if(random == 99 && random == 100 ) {
                                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+";"+"恭喜恭喜！！！Java界的奇才诞生了！！");
                                    }else {
                                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+";"+"垃圾！！！Java界的奇葩诞生了！！");

                                    }
                             }

                        }
              }else  if("image".equals(MsgType)) {

                    int random = (int)(Math.random() * 100);
                    if(random < 60) {
                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+";"+"废材！！！长相丑陋！！根骨奇差！！");
                    }else if(random >=60 && random <70) {
                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+";"+"中等！！！长相勉强！！可以练练，强身健体！！");
                    }else if (random >= 70 && random <80) {
                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+";"+"上等！！！长相清秀！！可以练功夫！！");
                    } else  if( random >=80 && random < 90) {
                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+"v"+"上上等！！！出生就是优势！！可以练任何功夫！！");
                    } else if (random >=90 && random <99) {
                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+";"+"优秀！！！已经好的无法评价了！！");
                    } else if(random == 99 && random == 100 ) {
                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+";"+"恭喜恭喜！！！Java界的奇才诞生了！！");
                    }else {
                        s = MessageUtil.initMessage(ToUserName,FromUserName,"评分："+random+";"+"垃圾！！！Java界的奇葩诞生了！！");

                    }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    /**
     * 文件上传
     * */
    @RequestMapping("/upload")
    public String upload(Model model,String type) throws IOException {
        AccessToken accessToken = WeChatUtil.getAccessToken();     //此处设计有很大的问题，，， 如何判断token 是否失效
        model.addAttribute("accessToken",accessToken.getToken());  //index.jsp上传文件后
        model.addAttribute("type",type);
        return  "index";
    }

}
