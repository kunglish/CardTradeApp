package com.example.coin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "user_table";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_ID = "id";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "App.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE user_table (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Username TEXT, Email TEXT, Password TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public String getpwd(String name){
        String querystring = "SELECT Password FROM " + USER_TABLE + " WHERE " + COLUMN_USERNAME + " LIKE '" + name + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(querystring,null);
        StringBuffer buffer = new StringBuffer();
        if(cursor.moveToFirst()){
            do{
                int index1 = cursor.getColumnIndex("Password");
                String pwd = cursor.getString(index1);
                buffer.append(pwd);
            }while(cursor.moveToNext());
            cursor.close();
            db.close();
            return buffer.toString();
        }else{
            return "Fail";
        }
    }

    public boolean addOne(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME,userModel.getUsername());
        cv.put(COLUMN_EMAIL,userModel.getEmail());
        cv.put(COLUMN_PASSWORD,userModel.getPassword());

        long insert = db.insert(USER_TABLE,null,cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

}
