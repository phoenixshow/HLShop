package com.phoenix.hlshop.entity;

import java.io.Serializable;

/**
 * Created by flashing on 2017/2/15.
 */
public class BaseBean implements Serializable {
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
