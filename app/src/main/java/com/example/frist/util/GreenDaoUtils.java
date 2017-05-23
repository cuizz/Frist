package com.example.frist.util;

import com.example.frist.greendao.gen.DaoSession;

/**
 * Created by Administrator on 2017/5/23.
 */

public class GreenDaoUtils {
   private DaoSession daoSession;
    public DaoSession getDaoSession(){
        if(daoSession==null){
            daoSession=GreenDaoManager.getInstance().getSession();
        }
        return daoSession;
    }
    /**
     *
     */
}
