package com.phoenix.hlshop.entity;

/**
 * Created by flashing on 2017/1/12.
 */
public class Tab {
    private int title;
    private int icon;
    private Class fragment;

    public Tab(Class fragment, int icon, int title) {
        this.fragment = fragment;
        this.icon = icon;
        this.title = title;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }
}
