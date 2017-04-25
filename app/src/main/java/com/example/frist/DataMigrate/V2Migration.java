package com.example.frist.DataMigrate;

import android.database.sqlite.SQLiteDatabase;

/**
 * 类名称：V2Migration
 * 创建者：Create by liujc
 * 创建时间：Create on 2016/12/5 16:28
 * 描述：针对V2版本数据库升级逻辑
 * 最近修改时间：2016/12/5 16:28
 * 修改人：Modify by liujc
 */
public class V2Migration implements Migration{
    @Override
    public void migrate(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE USER ADD COLUMN remark2");
    }
}
