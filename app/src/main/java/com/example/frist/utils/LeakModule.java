package com.example.frist.utils;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/14.
 */

public class LeakModule {

    public  static LeakModule singleton=null;
    private Context context;
    Toolbar mtv;
    public LeakModule(Context context){
        this.context=context;
    }
    public static LeakModule getInstance(Context context){
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if (singleton==null){
                //再次检查实例是否存在，如果不存在才真正的创建实例
                singleton=new LeakModule(context);
        }
        return singleton;
    }
    public void setText(Toolbar tv){
        mtv=tv;
        mtv.setTitle("泄露");
    }
}
