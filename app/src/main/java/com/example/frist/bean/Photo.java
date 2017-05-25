package com.example.frist.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2017/4/25.
 */
@Entity
public class Photo {
    @Id(autoincrement = true)
    private long id;
    private String name;
    @Generated(hash = 1724554123)
    public Photo(long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1043664727)
    public Photo() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
