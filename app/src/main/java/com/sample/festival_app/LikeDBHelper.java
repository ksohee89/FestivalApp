package com.sample.festival_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LikeDBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="MyLikeFestival.db";

    public LikeDBHelper(@Nullable Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS LikeNote (id INTEGER PRIMARY KEY AUTOINCREMENT, User TEXT NOT NULL, FestivalName TEXT NOT NULL, LikeTrue TEXT NOT NULL, StartDate  TEXT NOT NULL, EndDate TEXT NOT NULL, Opar TEXT NOT NULL, FestivalCo TEXT NOT NULL, Mnnst TEXT NOT NULL, PhoneNumber TEXT NOT NULL,Homepage TEXT NOT NULL,Rdnmadr TEXT NOT NULL,Latitude TEXT NOT NULL,Longitude TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }



    public ArrayList<LikeItem> getLikeList(String _user, String _likeTrue) {

        ArrayList<LikeItem> likeItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LikeNote WHERE User = '"+_user+"' AND LikeTrue== '"+_likeTrue+"' ", null);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String user = cursor.getString(1);
                String festivalName = cursor.getString(2);
                String startDate=cursor.getString(4);
                String endDate=cursor.getString(5);
                String opar=cursor.getString(6);
                String festivalCo=cursor.getString(7);
                String mnnst=cursor.getString(8);
                String phoneNumber=cursor.getString(9);
                String homepage=cursor.getString(10);
                String rdnmadr=cursor.getString(11);
                String latitude=cursor.getString(12);
                String longitude=cursor.getString(13);


                LikeItem likeItem = new LikeItem();
                likeItem.setId(id);
                likeItem.setUser(user);
                likeItem.setFestivalName(festivalName);
                likeItem.setStartDate(startDate);
                likeItem.setEndDate(endDate);
                likeItem.setOpar(opar);
                likeItem.setFestivalCo(festivalCo);
                likeItem.setMnnst(mnnst);
                likeItem.setPhoneNumber(phoneNumber);
                likeItem.setHomepage(homepage);
                likeItem.setRdnmadr(rdnmadr);
                likeItem.setLatitude(latitude);
                likeItem.setLongitude(longitude);

                likeItems.add(likeItem);

            }
        }

        cursor.close();

        return likeItems;
    }


    public ArrayList<LikeItem> LikeList(String _festivalName) {

        ArrayList<LikeItem> likeItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LikeNote WHERE  FestivalName = '"+_festivalName+"'", null);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String user = cursor.getString(1);
                String festivalName = cursor.getString(2);
                String likeTrue=cursor.getString(3);
                String startDate=cursor.getString(4);
                String endDate=cursor.getString(5);
                String opar=cursor.getString(6);
                String festivalCo=cursor.getString(7);
                String mnnst=cursor.getString(8);
                String phoneNumber=cursor.getString(9);
                String homepage=cursor.getString(10);
                String rdnmadr=cursor.getString(11);
                String latitude=cursor.getString(12);
                String longitude=cursor.getString(13);


                LikeItem likeItem = new LikeItem();
                likeItem.setId(id);
                likeItem.setUser(user);
                likeItem.setFestivalName(festivalName);
                likeItem.setLikeTrue(likeTrue);
                likeItem.setStartDate(startDate);
                likeItem.setEndDate(endDate);
                likeItem.setOpar(opar);
                likeItem.setFestivalCo(festivalCo);
                likeItem.setMnnst(mnnst);
                likeItem.setPhoneNumber(phoneNumber);
                likeItem.setHomepage(homepage);
                likeItem.setRdnmadr(rdnmadr);
                likeItem.setLatitude(latitude);
                likeItem.setLongitude(longitude);

                likeItems.add(likeItem);

            }
        }

        cursor.close();

        return likeItems;
    }

    public String getLike(String _user, String _festivalName) {

        String likeTrue=new String();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LikeNote WHERE User = '"+_user+"' AND FestivalName = '"+_festivalName+"'", null);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String user = cursor.getString(1);
                String festivalName = cursor.getString(2);
                likeTrue=cursor.getString(3);

            }
        }

        else if(cursor.getCount()==0){
            likeTrue="NoExist";
        }

        cursor.close();

        return likeTrue;
    }


    public void Insert(String _user, String _festivalName, String _likeTrue, String _startDate, String _endDate, String _opar,String _festivalCo,String _mnnst, String _phoneNumber, String _homepage,String _rdnmadr,String _latitude, String _longitude){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO LikeNote(User,FestivalName,LikeTrue,StartDate,EndDate,Opar,FestivalCo,Mnnst,PhoneNumber,Homepage,Rdnmadr,Latitude,Longitude) VALUES ('"+_user+"','"+_festivalName+"','"+_likeTrue+"','"+_startDate+"','"+_endDate+"','"+_opar+"','"+_festivalCo+"','"+_mnnst+"','"+_phoneNumber+"','"+_homepage+"','"+_rdnmadr+"','"+_latitude+"','"+_longitude+"');");
    }

    public void LikeUpdate(String _user, String _festivalName,String _likeTrue,String _startDate,String _endDate,String _opar,String _festivalCo,String _mnnst,String _phoneNumber,String _homepage, String _rdnmadr,String _latitude, String _longitude){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE LikeNote SET LikeTrue='"+_likeTrue+"', StartDate='"+_startDate+"',EndDate='"+_endDate+"',Opar='"+_opar+"',FestivalCo='"+_festivalCo+"',Mnnst='"+_mnnst+"',PhoneNumber='"+_phoneNumber+"',Homepage='"+_homepage+"',Rdnmadr='"+_rdnmadr+"',Latitude='"+_latitude+"',Longitude='"+_longitude+"' WHERE User='"+_user+"' AND FestivalName='"+_festivalName+"'");
    }

    public void Delete(String _user, String _festivalName){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM LikeNote WHERE User='"+_user+"' AND FestivalName='"+_festivalName+"'");
    }

}
