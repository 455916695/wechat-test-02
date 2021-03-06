package com.ax.utils;

import com.ax.pojo.*;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {

    public static final String MESSAGE_TEXT = "text";

    public static final String MESSAGE_IMAGE = "image";

    public static final String MESSAGE_NEWS = "news";
    public static  final  String MESSAGE_MUSIC = "music";
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
    public  static String objectToXml(TextMessage message) {
        XStream xStream = new XStream();
        //由于转换后xml根节点默认为class类，需转化为<xml>
//        xStream.alias("xml",Message.class);
        xStream.alias("xml",message.getClass());
        return  xStream.toXML(message);
    }

    /**
     *   将图文消息对象转化为xml格式
     * @param imageMessage
     * @return  String
     * */
    public  static String newsMessageToXml (NewsMessage imageMessage) {
        XStream xstream = new XStream();
        //将xml的根节点替换成<xml>  默认为NewsMessage的包名
        xstream.alias("xml", imageMessage.getClass());
        //同理，将每条图文消息News类的报名，替换为<item>标签
        xstream.alias("item", new News().getClass());
        return xstream.toXML(imageMessage);

    }

    /**
     * 将 imageMessage 转化为 xml
     * */
    public static  String imageMessageToXml(ImageMessage imageMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml",imageMessage.getClass());
        return  xStream.toXML(imageMessage);  //将imageMessage 转换为xml格式的数据
    }

    /**
     * 将 imageMessage 转化为 xml
     * */
    public static  String musicMessageToXml(MusicMessage musicMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml",musicMessage.getClass());
        return  xStream.toXML(musicMessage);  //将imageMessage 转换为xml格式的数据
    }


    /**
     *  主菜单消息方法
     * */
    public static String menuMessage() {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("欢迎关注本公众号：这是一次新的尝试！请选择：\n\n");
        stringBuffer.append("1.Java是世界上最好的语言！！！\n\n");
        stringBuffer.append("2.Java不是世界上最好的语言！！！\n\n");
        stringBuffer.append("3.阿闲界入口！！！\n\n");
        stringBuffer.append("4.查看本人照片,慎回！慎回！慎回！\r\n");
        stringBuffer.append("5.来首音乐！\r\n");
        stringBuffer.append("回复 ? 调出主菜单。\n\n");
        return stringBuffer.toString();
    }

    /**
     * 初始化消息
     * */
    public static   String initMessage(String ToUserName,String FromUserName,String Content) {

        TextMessage message = new TextMessage();
        message.setFromUserName(ToUserName); //？？？ 此处存在问题，参数的意义是什么
        message.setToUserName(FromUserName);
        message.setMsgType(MessageUtil.MESSAGE_TEXT);
        message.setCreateTime(System.currentTimeMillis());
        message.setContent(Content);

        String xml = MessageUtil.objectToXml(message);

        return xml;
    }

    /**
     * 初始化图文消息
     * */
    public static   String initNewsMessage(String ToUserName,String FromUserName ) {

        List<News> imagesList = new ArrayList<>();

        News image = new News();
        image.setTitle("欢迎来到阿闲界");
        image.setDescription("阿闲界，闲到你无可奈何！！！\r\n点击即可解决您的疑问");
        image.setPicUrl("http://pic54.nipic.com/file/20141201/13740598_112413393000_2.jpg");  //
        image.setUrl("www.baidu.com");   //图片背后的链接

        imagesList.add(image);

         NewsMessage imageMessage = new NewsMessage();

         imageMessage.setToUserName(FromUserName);      // 消息接收者
         imageMessage.setFromUserName(ToUserName);     //  消息发送者
         imageMessage.setCreateTime(System.currentTimeMillis());  //设置当前时间
         imageMessage.setArticles(imagesList);  //设置图文内容
         imageMessage.setArticleCount(imagesList.size()); //设置条数
         imageMessage.setMsgType(MessageUtil.MESSAGE_NEWS);  //设置消息类型


        String xml = MessageUtil.newsMessageToXml(imageMessage);
        //System.out.println(xml);
        return xml;
    }

    /**
     * 组装图片信息
     * */
    public static String  initImageMessage(String  toUserName,String fromUserName) {

        String message = "";

        String mediaId  = "3lxrMsO1qpBf8jRE3IE4voUlzZ1QinliCa23Y_oe1yIX1nphzPPmqi64TlyUM3S6";

        Image image = new Image();
        image.setMediaId(mediaId);

        ImageMessage  imageMessage = new ImageMessage();
        imageMessage.setToUserName(fromUserName);
        imageMessage.setFromUserName(toUserName);
        imageMessage.setMsgType(MessageUtil.MESSAGE_IMAGE);
        imageMessage.setCreateTime(System.currentTimeMillis());
        imageMessage.setImage(image);

        message =  MessageUtil.imageMessageToXml(imageMessage);

        return  message;
    }

    /**
     *  c初始化音乐，并将其转换为xml格式
     * */
    public static String initMusicMessage (String ToUserName,String ForUserName) {
       String message = "";

       String thumbMediaId = "NMiDaUqp64j1lI3cq7n9B-b1ELsHyFpo4_tWW5PPCNAn3se9qbFES8bI612UNoKM"; //thumb_media_id":"NMiDaUqp64j1lI3cq7n9B-b1ELsHyFpo4_tWW5PPCNAn3se9qbFES8bI612UNoKM"
       Music music = new Music();
       music.setThumbMediaId(thumbMediaId);  //缩略图ID
       music.setTitle("阿闲乐");       //标题
       music.setDescription("阿闲常听的音乐"); //描述
        music.setMusicUrl("http://ax2.free.idcfengye.com/music/1.mp3");  //音乐链接
        music.setHQMusicUrl("http://ax2.free.idcfengye.com/music/1.mp3");  //高清链接

        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setMusic(music);
        musicMessage.setToUserName(ForUserName);
        musicMessage.setFromUserName(ToUserName);
        musicMessage.setMsgType(MESSAGE_MUSIC);
        musicMessage.setCreateTime(System.currentTimeMillis());

        message = MessageUtil.musicMessageToXml(musicMessage);

        return message;
    }

}
