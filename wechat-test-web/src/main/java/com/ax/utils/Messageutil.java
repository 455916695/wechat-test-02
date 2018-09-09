package com.ax.utils;

import com.ax.pojo.Message;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Messageutil {

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
}
