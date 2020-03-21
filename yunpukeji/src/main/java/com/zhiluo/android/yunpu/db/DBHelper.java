package com.zhiluo.android.yunpu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zhiluo.android.yunpu.utils.MySQLiteHelperUtil;

/**
 * iauthor：Cheng
 * date: 2017/6/1 09:20
 * email：jasoncheng9111@gmail.com
 */
public class DBHelper
{
    private MySQLiteHelperUtil mMySQLiteHelperUtil;

    public DBHelper(Context context)
    {
        mMySQLiteHelperUtil = DBManager.getInstance(context);
    }

    /**
     * 创建数据库
     */
    public void createDB()
    {
        /**
         * 如果数据库不存在则创建数据库，如果数据库存在则直接打开数据库
         */
        SQLiteDatabase db = mMySQLiteHelperUtil.getWritableDatabase();
    }
}
