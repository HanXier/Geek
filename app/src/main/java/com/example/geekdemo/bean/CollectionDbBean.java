package com.example.geekdemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CollectionDbBean {

    @Id
    private Long Lid;

    private String id;
    private String title;
    private String imgUrl;
    private int fromType;

    @Generated(hash = 1999791114)
    public CollectionDbBean(Long Lid, String id, String title, String imgUrl, int fromType) {
        this.Lid = Lid;
        this.id = id;
        this.title = title;
        this.imgUrl = imgUrl;
        this.fromType = fromType;
    }

    @Generated(hash = 1521980762)
    public CollectionDbBean() {
    }

    public Long getLid() {
        return Lid;
    }

    public void setLid(Long lid) {
        Lid = lid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getFromType() {
        return fromType;
    }

    public void setFromType(int fromType) {
        this.fromType = fromType;
    }
}
