package com.example.frist;

import android.app.Application;
import android.content.Context;

import com.example.frist.util.GreenDaoManager;

/**
 * Created by Administrator on 2017/4/25.
 */

public class TApplication extends Application{
    public  static Context instance;
    @Override
    public void onCreate() {
        super.onCreate();
        if(instance==null){
            instance=getApplicationContext();
        }
        GreenDaoManager.getInstance();
    }
    public static Context getInstance(){
        return instance;
    }
}
