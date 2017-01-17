package cn.ucai.fulicenter.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String FULICENTER_USER_TABLE_CREATE =
            "CREATE TABLE "+UserDao.USER_TABLE_NAME+" ("
            +UserDao.USER_COLUMN_NAME+" TEXT PRIMARY KEY,"
            +UserDao.USER_COLUMN_NICK+" TEXT,"
            +UserDao.USER_COLUMN_AVATAR+" INITEGER,"
            +UserDao.USER_COLUMN_AVATAR_PATH+" TEXT,"
            +UserDao.USER_COLUMN_ACATAR_TYPE+" INTEGER,"
            +UserDao.USER_COLUMN_ACATAR_SUFFIX+" TEXT,"
            +UserDao.USER_COLUMN_ACATAR_UPDATE_TIME+" TEXT);";
    private static DBOpenHelper instance;
    public static DBOpenHelper getInstance(Context context){
        if(instance == null){
            instance = new DBOpenHelper(context);
        }
        return instance;
    }
    public DBOpenHelper(Context context) {
        super(context, getUserDataBaseName(), null, DATABASE_VERSION);
    }

    private static String getUserDataBaseName() {
        return "cn.ucai.fulicent.db";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FULICENTER_USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private void closeDB(){
        if(instance!=null){
            SQLiteDatabase db = instance.getWritableDatabase();
            db.close();
            instance = null;
        }
    }
}
