package com.example.frist.DataMigrate;

import android.database.sqlite.SQLiteDatabase;

/**
 * 类名称：Migration
 * 创建者：Create by liujc
 * 创建时间：Create on 2016/12/5 16:27
 * 描述：数据迁移接口
 * 最近修改时间：2016/12/5 16:27
 * 修改人：Modify by liujc
 */
public interface Migration {
    void migrate(SQLiteDatabase db);
}
