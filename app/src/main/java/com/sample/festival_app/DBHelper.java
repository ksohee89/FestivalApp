package com.sample.festival_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "festival.db";

    public DBHelper(@Nullable Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Note(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL, title TEXT NOT NULL, memo TEXT NOT NULL, writeDate TEXT NOT NULL, duration TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public ArrayList<MemoItem> getMemoList(String _name) {
        ArrayList<MemoItem> memoItems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Note WHERE name = '"+_name+"' ORDER BY writeDate DESC", null);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(2);
                String memo = cursor.getString(3);
                String writeDate = cursor.getString(4);
                String duration = cursor.getString(5);

                MemoItem memoItem = new MemoItem();
                memoItem.setId(id);
                memoItem.setTitle(title);
                memoItem.setMemo(memo);
                memoItem.setWriteDate(writeDate);
                memoItem.setDuration(duration);

                memoItems.add(memoItem);

            }
        }
        cursor.close();

        return memoItems;
    }


    public ArrayList<MemoItem> getMemoList_calender(String str, String _name) {
        ArrayList<MemoItem> memoItems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Note WHERE duration like '%"+str+"%' AND name = '"+_name+"'  ORDER BY writeDate DESC", null);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(2);
                String memo = cursor.getString(3);
                String writeDate = cursor.getString(4);
                String duration = cursor.getString(5);

                MemoItem memoItem = new MemoItem();
                memoItem.setId(id);
                memoItem.setTitle(title);
                memoItem.setMemo(memo);
                memoItem.setWriteDate(writeDate);
                memoItem.setDuration(duration);

                memoItems.add(memoItem);

            }
        }
        cursor.close();

        return memoItems;
    }

    //INSERT
    public void save(String _name, String _title, String _memo, String _writeDate, String _duration) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Note(name, title,memo,writeDate,duration) VALUES ('"+_name+"','"+_title+"','"+_memo+"','"+_writeDate+"','"+_duration+"');");
    }

    //UPDATE
    public void Update(String _title, String _memo, String _writeDate, String _beforeDate, String _duration){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Note SET title='"+ _title+"',memo='"+ _memo+"',writeDate='"+ _writeDate+"',duration='"+_duration+"' WHERE writeDate='"+ _beforeDate +"'");
    }

    //DELETE
    public void delete(String _beforeDate){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Note WHERE writeDate='"+ _beforeDate+"'");
    }
}
