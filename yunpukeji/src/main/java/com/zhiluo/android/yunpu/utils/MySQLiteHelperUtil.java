package com.zhiluo.android.yunpu.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zhiluo.android.yunpu.db.DBConstant;
import com.zhiluo.android.yunpu.db.table.BlueToothTable;
import com.zhiluo.android.yunpu.db.table.MessageTable;

/**
 * 数据库帮助类
 * 提供获取数据库对象的函数
 * iauthor：Cheng
 * date: 2017/6/1 09:00
 * email：jasoncheng9111@gmail.com
 */
public class MySQLiteHelperUtil extends SQLiteOpenHelper
{
    /**
     * 构造函数
     * @param context 上下文对象
     * @param name  表示创建数据库的的名称
     * @param factory   游标工厂
     * @param version   表示创建数据的版本>=1
     */
    public MySQLiteHelperUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    public MySQLiteHelperUtil(Context context)
    {
        super(context, DBConstant.DATABASE_NAME, null, DBConstant.DATABASE_VERSION);
    }

    /**
     * 当创建数据库时回调的函数
     * @param sqLiteDatabase 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String sql = "CREATE TABLE IF NOT EXISTS "
                + BlueToothTable.TABLE_NAME
                +"("+BlueToothTable.ID+" Integer primary key autoincrement, "
                +BlueToothTable.NAME+" varchar(50), "
                +BlueToothTable.MAC_ADDRESS+" varchar(50), "
                +BlueToothTable.BINDING_STATUS+" Integer, "
                +BlueToothTable.CONNECT_STATUS+" Integer)";

        String sql2 = "CREATE TABLE IF NOT EXISTS "+ MessageTable.TABLE_NAME
                +"("+MessageTable.ID+" Integer primary key,"
                +MessageTable.TITLE+" varchar(50),"
                +MessageTable.READ_STATUS+" Integer)";

        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);

        Log.d("TAG", "创建了数据库");
    }

    /**
     * 当数据库版本更新的时候回调的函数
     * @param sqLiteDatabase 数据库对象
     * @param i 数据库老版本
     * @param i1 数据库新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        if (i1 > i)
        {
            Log.d("TAG", "更新了数据库: ");
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
        Log.d("TAG", "打开了数据库: ");

    }
}
