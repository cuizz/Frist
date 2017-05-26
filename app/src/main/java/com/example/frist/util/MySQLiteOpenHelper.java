package com.example.frist.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.frist.DataMigrate.Migration;
import com.example.frist.DataMigrate.MigrationHelper;
import com.example.frist.DataMigrate.V1Migration;
import com.example.frist.DataMigrate.V2Migration;
import com.example.frist.bean.Photo;
import com.example.frist.greendao.gen.DaoMaster;
import com.example.frist.greendao.gen.PhotoDao;
import com.example.frist.greendao.gen.StudentDao;
import com.example.frist.greendao.gen.TeacherDao;
import com.example.frist.greendao.gen.UserDao;
import com.example.frist.greendao.gen.UsersDao;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 类名称：MySQLiteOpenHelper
 * 创建者：Create by liujc
 * 创建时间：Create on 2016/12/5 11:03
 * 描述：
 * 最近修改时间：2016/12/5 11:03
 * 修改人：Modify by liujc
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper  {
    private static final SortedMap<Integer, Migration> ALL_MIGRATIONS = new TreeMap<>();
    static {
        ALL_MIGRATIONS.put(1, new V1Migration());
        ALL_MIGRATIONS.put(2, new V2Migration());
    }
    public MySQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

//    @Override
//    public void onCreate(Database db) {
//        super.onCreate(db);
//        executeMigrations(db, ALL_MIGRATIONS.keySet());
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
        MigrationHelper.migrate(db,
                UserDao.class,
                StudentDao.class,
                TeacherDao.class,
                PhotoDao.class,
                UsersDao.class);

//        SortedMap<Integer, Migration> migrations = ALL_MIGRATIONS.subMap(oldVersion, newVersion);
//        executeMigrations(db, migrations.keySet());

    }
    private void executeMigrations(final SQLiteDatabase paramSQLiteDatabase, final Set<Integer>
            migrationVersions) {
        for (final Integer version : migrationVersions) {
            ALL_MIGRATIONS.get(version).migrate(paramSQLiteDatabase);
        }
    }
}
