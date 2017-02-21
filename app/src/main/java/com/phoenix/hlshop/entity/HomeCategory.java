package com.phoenix.hlshop.entity;

/**
 * Created by flashing on 2017/2/15.
 */
public class HomeCategory extends Category {
    private int imgBig;
    private int imgSmallTop;
    private int imgSmallBottom;

    public HomeCategory(String name, int imgBig, int imgSmallTop, int imgSmallBottom){
        super(name);
        this.imgBig = imgBig;
        this.imgSmallTop = imgSmallTop;
        this.imgSmallBottom = imgSmallBottom;
    }

    public int getImgBig() {
        return imgBig;
    }

    public void setImgBig(int imgBig) {
        this.imgBig = imgBig;
    }

    public int getImgSmallBottom() {
        return imgSmallBottom;
    }

    public void setImgSmallBottom(int imgSmallBottom) {
        this.imgSmallBottom = imgSmallBottom;
    }

    public int getImgSmallTop() {
        return imgSmallTop;
    }

    public void setImgSmallTop(int imgSmallTop) {
        this.imgSmallTop = imgSmallTop;
    }
}
