package com.ax.pojo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsMessage extends Message {
    private  int ArticleCount;  //图文消息条数
    private List<News> Articles;

    public NewsMessage() {
    }

    public NewsMessage(String ToUserName, String FromUserName, Long CreateTime, String MsgType, String Content, String MsgId, int ArticleCount, List<News> Articles) {
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

    public List<News> getArticles() {
        return Articles;
    }

    public void setArticles(List<News> Articles) {
        this.Articles = Articles;
    }
}
