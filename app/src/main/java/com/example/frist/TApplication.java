package com.example.frist;

import android.app.Application;
import android.content.Context;

import com.example.frist.util.GreenDaoManager;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

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
        Stetho.initializeWithDefaults(this);
       // if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
          //  return;
      //  }
        LeakCanary.install(this);
    }
    public static Context getInstance(){
        return instance;
    }
}
