package com.ax.pojo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageMessage extends Message {
    private  int ArticleCount;  //图文消息条数
    private List<Image> Articles;

    public ImageMessage() {
    }

    public ImageMessage(String ToUserName, String FromUserName, Long CreateTime, String MsgType, String Content, String MsgId, int ArticleCount, List<Image> Articles) {
        super(ToUserName, FromUserName, CreateTime, MsgType, Content, MsgId);
        this.ArticleCount = ArticleCount;
        this.Articles = Articles;
    }

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int ArticleCount) {
        this.ArticleCount = ArticleCount;
    }

    public List<Image> getArticles() {
        return Articles;
    }

    public void setArticles(List<Image> Articles) {
        this.Articles = Articles;
    }
}
