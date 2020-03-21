package com.zhiluo.android.yunpu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zhiluo.android.yunpu.utils.MySQLiteHelperUtil;

/**
 * 这是一个数据库对象的单例程序
 * iauthor：Cheng
 * date: 2017/6/1 09:20
 * email：jasoncheng9111@gmail.com
 */
public class DBManager
{
    private static MySQLiteHelperUtil sMySQLiteHelperUtil;

    public static MySQLiteHelperUtil getInstance(Context context)
    {
        if (sMySQLiteHelperUtil == null)
        {
            sMySQLiteHelperUtil = new MySQLiteHelperUtil(context);
        }
        return sMySQLiteHelperUtil;
    }

    public static void execSQL(SQLiteDatabase db, String sql)
    {
        if (db != null)
        {
            if (sql != null && !"".equals(sql))
            {
                db.execSQL(sql);
            }
        }
    }
}
