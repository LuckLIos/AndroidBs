package com.example.lucklios.zuihoudebiyesheji;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by lucklios on 2018/5/27.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_PERSION="create table person("+"id integer primary key autoincrement,"+"name text,"+"password text)";
    private Context mContext;
    public MyDataBaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PERSION);
        Toast.makeText(mContext,"succeed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
