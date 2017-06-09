package com.example.frist.bean;

/**
 * Created by Administrator on 2017/5/27.
 */

public class CheckBean {
    private long id;
    private String name;
    private String year;
    private long stuId;
    private boolean isture;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public long getStuId() {
        return stuId;
    }

    public void setStuId(long stuId) {
        this.stuId = stuId;
    }

    public boolean isture() {
        return isture;
    }

    public void setIsture(boolean isture) {
        this.isture = isture;
    }
}
