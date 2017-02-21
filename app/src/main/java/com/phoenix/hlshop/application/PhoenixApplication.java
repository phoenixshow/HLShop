package com.phoenix.hlshop.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by flashing on 2016/9/19.
 */
public class PhoenixApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        //基本使用的初始化方法
        Fresco.initialize(this);
    }
}
