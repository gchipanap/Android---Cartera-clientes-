package com.example.clientwallet.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataOpenHelper extends SQLiteOpenHelper
{
    public DataOpenHelper(Context context)
    {
        super(context, "DATA", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS CLIENT (");
        sql.append("NAME VARCHAR(256), ");
        sql.append("ADDRESS VARCHAR(256), ");
        sql.append("EMAIL VARCHAR(200), ");
        sql.append("PHONE VARCHAR(20))");

        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        //
    }
}
