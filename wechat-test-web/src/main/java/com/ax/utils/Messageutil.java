package com.ax.utils;

import com.ax.pojo.Message;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {

    public static final String MESSAGE_TEXT = "text";

    public static final String MESSAGE_IMAGE = "image";

    public static final String MESSAGE_VOICE = "voice";

    public static final String MESSAGE_VIDEO = "video";

    public static final String MESSAGE_SHORTVIDEO = "shortvideo";

    public static final String MESSAGE_LINK = "link";

    public static final String MESSAGE_LOCATION = "location";

    public static final String MESSAGE_EVENT = "event";

    public static final String MESSAGE_SUBSCRIBE = "subscribe";

    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";

    public static final String MESSAGE_CLICK = "CLICK";

    public static final String MESSAGE_VIEW = "VIEW";

    public static final String MESSAGE_SCAN = "SCAN";


    /**
     * 将xml格式数据 转化为 Map
     * @param request
     * @return Map
     * */
    public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String ,String > map =  new HashMap<>();
        //------
        SAXReader saxReader = new SAXReader();

        InputStream inputStream = request.getInputStream(); //从request中，获取输入流
        Document document = saxReader.read(inputStream); //总reader对象中，读取输入流

        Element rootElement = document.getRootElement();//获取XML文档的根元素
        List<Element> elements = rootElement.elements(); //获取根元素下的所有子节点
        for(Element element : elements) {     //遍历所有的子节点
            String name = element.getName();   //获取name
            String text = element.getText();   //获取text
            map.put(name,text);               //存入map
        }

        inputStream.close();  //关闭流
        return map;
    }

    /**
     * 将文本消息对象转化为xml格式
     *  @param  message
     *  @return  String
     * */
    public  static String objectToXml(Message message) {
        XStream xStream = new XStream();
        //由于转换后xml根节点默认为class类，需转化为<xml>
//        xStream.alias("xml",Message.class);
        xStream.alias("xml",message.getClass());
        return  xStream.toXML(message);
    }

    /**
     *  主菜单消息方法
     * */
    public static String menuMessage() {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("欢迎关注本公众号：这是一次新的尝试！请选择：\n\n");
        stringBuffer.append("1.Java是世界上最好的语言！！！\n\n");
        stringBuffer.append("2.Java不是世界上最好的语言！！！\n\n");
        stringBuffer.append("回复 ? 调出主菜单。\n\n");
        return stringBuffer.toString();
    }

    /**
     * 初始化消息
     * */
    public static   String initMessage(String ToUserName,String FromUserName,String content) {

        Message message = new Message();
        message.setFromUserName(ToUserName); //？？？ 此处存在问题，参数的意义是什么
        message.setToUserName(FromUserName);
        message.setMsgType(MessageUtil.MESSAGE_TEXT);
        message.setCreateTime(System.currentTimeMillis());
        message.setContent(content);

        String xml = MessageUtil.objectToXml(message);

        return xml;
    }

}
