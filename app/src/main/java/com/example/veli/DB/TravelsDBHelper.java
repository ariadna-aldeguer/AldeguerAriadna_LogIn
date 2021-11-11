package com.example.veli.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.veli.DB.TravelsContract.*;
import com.example.veli.Model.Travel;

import java.util.ArrayList;

/**
 * A helper class to manage database creation and version management.
 */

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

    /**
     * Constructor of class TravelDBHelper
     */
    public TravelsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time.
     * It creates de tables for the database.
     * @param db: database instance
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     * Called when the database needs to be upgraded.
     * @param db: database instance
     * @param i: oldVersion
     * @param i1: newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //TODO
    }

    /**
     * Inserts a travel instance into the database
     * @param db: database instance
     * @param t: instance of travel
     */
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
        } else {
            Log.i("sql","Database is closed");
        }
    }
    public void updateTravel(SQLiteDatabase db, Travel oldTravel, Travel newTravel){
        //Check the bd is open
        if (db.isOpen()){
            //Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();
            db.execSQL("UPDATE " + TravelsEntry.TABLE_NAME + " SET " +
                    TravelsEntry.COLUMN_NAME_COUNTRY + " = '" + newTravel.getCountry() + "', " +
                    TravelsEntry.COLUMN_NAME_CITY + " = '" + newTravel.getCity() + "', " +
                    TravelsEntry.COLUMN_NAME_AIRPORT + " = '" + newTravel.getAirport() + "'" +
                    " WHERE ID = " + oldTravel.getId());
        } else {
            Log.i("sql","Database is closed");
        }
    }


    /**
     * Getter of travels of the database
     * @param db: database instance
     * @return an arraylist of Travels
     */
    public ArrayList<Travel> getTravels(SQLiteDatabase db){

        ArrayList<Travel> data= new ArrayList<Travel>();
        Cursor cursor = db.query(TravelsEntry.TABLE_NAME, new String[]{"id", "country", "city", "airport"},null, null, null, null, null);
        Travel t;
        while (cursor.moveToNext()) {
            t = new Travel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            data.add(t);
        }
        cursor.close();
        return data;
    }

    /**
     * Deletes a travel of database by id
     * @param db: database instance
     * @param id: id of the travel
     */
    public void deleteTravel(SQLiteDatabase db, int id){

        if (db.isOpen()){
            String ID = String.valueOf(id);
            db.execSQL("DELETE FROM " + TravelsEntry.TABLE_NAME + " WHERE ID = " + ID);
        } else {
            Log.i("sql","Database is closed");
        }
    }

    /**
     * Deletes all travels of database
     * @param db: database instance
     */
    public void deleteAllTravels(SQLiteDatabase db){

        if (db.isOpen()){
            db.delete(TravelsEntry.TABLE_NAME, null, null);
        } else {
            Log.i("sql","Database is closed");
        }
    }
}
