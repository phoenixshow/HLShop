package com.phoenix.hlshop.entity;

/**
 * Created by flashing on 2017/2/17.
 */
public class Campaign extends BaseBean {
    private String title;
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
