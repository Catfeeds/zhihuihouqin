package cn.lc.model.ui.main.bean;

import java.io.Serializable;

/**
 * Created by 我的电脑 on 2017/9/9 0009.
 */

public class BooklistBean implements Serializable{

    private String author;
    private int bollowstatus;
    private int id;
    private String img;
    private String publisher;
    private int score;
    private String shortbrief;
    private String title;
    private int typeid;
    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBollowstatus() {
        return bollowstatus;
    }

    public void setBollowstatus(int bollowstatus) {
        this.bollowstatus = bollowstatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getShortbrief() {
        return shortbrief;
    }

    public void setShortbrief(String shortbrief) {
        this.shortbrief = shortbrief;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}