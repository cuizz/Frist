package com.example.frist.util;

import com.example.frist.TApplication;
import com.example.frist.greendao.gen.DaoMaster;
import com.example.frist.greendao.gen.DaoSession;

/**
 * Created by Administrator on 2017/4/25.
 */

public class GreenDaoManager {
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static volatile GreenDaoManager mInstance = null;
    private GreenDaoManager(){
        if (mInstance == null) {
           // DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(TApplication.getInstance(), "user.db");
            MySQLiteOpenHelper devOpenHelper = new
                    MySQLiteOpenHelper(new GreenDaoContext(), "database_name.db", null);
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }
    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {                if (mInstance == null) {
                mInstance = new GreenDaoManager();
            }
            }
        }
        return mInstance;
    }
    public DaoMaster getMaster() {
        return mDaoMaster;
    }
    public DaoSession getSession() {
        return mDaoSession;
    }
    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
