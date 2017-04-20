package com.example.frist.http;

/**
 * Created by Administrator on 2017/4/20.
 */

public class HttpEvent {
    String content;
    int tsg;

    public HttpEvent(String content, int tsg) {
        this.content = content;
        this.tsg = tsg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTsg() {
        return tsg;
    }

    public void setTsg(int tsg) {
        this.tsg = tsg;
    }
}
