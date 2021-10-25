package com.example.login.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.login.DB.TravelsContract.*;
import com.example.login.FormFragment;
import com.example.login.ListFragment;
import com.example.login.Model.Travel;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TravelsDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "travels.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            TravelsEntry.TABLE_NAME +
            "("
            + TravelsEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TravelsEntry.COLUMN_NAME_COUNTRY + " TEXT, " +
            TravelsEntry.COLUMN_NAME_CITY + " TEXT, " +
            TravelsEntry.COLUMN_NAME_AIRPORT + " TEXT " +
            ")";


    public TravelsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void dropAllTravels(SQLiteDatabase db){
        //Check the bd is open
        if (db.isOpen()){
            db.delete(TravelsEntry.TABLE_NAME, null, null);
        }else{
            Log.i("sql","Database is closed");
        }
    }
    public void insertTravel(SQLiteDatabase db, Travel t){
        //Check the bd is open
        if (db.isOpen()){
            //Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();

            //Insert the contacts getting all values
            values.put(TravelsEntry.COLUMN_NAME_COUNTRY, t.getCountry());
            values.put(TravelsEntry.COLUMN_NAME_CITY, t.getCity());
            values.put(TravelsEntry.COLUMN_NAME_AIRPORT, t.getAirport());
            db.insert(TravelsEntry.TABLE_NAME, null, values);
        }else{
            Log.i("sql","Database is closed");
        }
    }

    public ArrayList<Travel> getTravels(SQLiteDatabase db){
        ArrayList<Travel> data=new ArrayList<Travel>();
        Cursor cursor = db.query(TravelsEntry.TABLE_NAME, new String[]{"id", "country", "city", "airport"},null, null, null, null, null);
        Travel t;
        while(cursor.moveToNext()){
            t = new Travel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            data.add(t);
        }
        cursor.close();
        return data;
    }
    public void deleteTravel(SQLiteDatabase db, int id){
        if (db.isOpen()){
            String ID = String.valueOf(id);
            Log.i("test tests", ID);
            db.execSQL("DELETE FROM " + TravelsEntry.TABLE_NAME + " WHERE ID = " + ID);
        }else{
            Log.i("test sql","Database is closed");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
