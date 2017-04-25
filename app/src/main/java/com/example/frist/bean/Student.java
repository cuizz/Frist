package com.example.frist.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/4/25.
 */
@Entity
public class Student {
    @Id
    private long id;
    @Property(nameInDb = "sex")
    private String name;
    private long stuId;
    @Generated(hash = 2058730800)
    public Student(long id, String name, long stuId) {
        this.id = id;
        this.name = name;
        this.stuId = stuId;
    }
    @Generated(hash = 1556870573)
    public Student() {
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
    public long getStuId() {
        return this.stuId;
    }
    public void setStuId(long stuId) {
        this.stuId = stuId;
    }
}
