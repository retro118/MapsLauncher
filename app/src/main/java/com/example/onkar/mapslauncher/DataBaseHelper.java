package com.example.onkar.mapslauncher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME= "BusDatabase.db";

    public static final String TABLE_NAME= "BusTable";

    public static final String COL_1= "BusNumber";
    public static final String COL_2= "StartingPoint";
    public static final String COL_3= "FinalPoint";
    public static final String COL_4= "Departure";
    public static final String COL_5= "Distance";
    public static final String COL_6= "Price";
    public static final String COL_7= "Latitude";
    public static final String COL_8= "Longitude";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                " ( " +
                COL_1 + " INTEGER PRIMARY KEY," +
                COL_2 + " TEXT," +
                COL_3 + " TEXT,"+
                COL_4 + " TEXT,"+
                COL_5 + " TEXT,"+
                COL_6 + " TEXT,"+
                COL_7 + " TEXT,"+
                COL_8 + " TEXT ) ";
        db.execSQL(query);

        // insertion in the table
        ContentValues values = new ContentValues();
        values.put(COL_1, "12");
        values.put(COL_2 , "LOHGAON");
        values.put(COL_3 , "KATRAJ");
        values.put(COL_4 , "8:00");
        values.put(COL_5 , "16");
        values.put(COL_6 , "25");
        values.put(COL_7 , "18.457532399999998");
        values.put(COL_8 , "73.8677464");

        db.insert(TABLE_NAME, null, values);

        values.put(COL_1, "13");
        values.put(COL_2 , "LOHGAON");
        values.put(COL_3 , "ShivajiNagar");
        values.put(COL_4 , "8:30");
        values.put(COL_5 , "10");
        values.put(COL_6 , "25");
        values.put(COL_7 , "18.5308225");
        values.put(COL_8 , "73.8474647");

        db.insert(TABLE_NAME, null, values);


        values.put(COL_1, "14");
        values.put(COL_2 , "LOHGAON");
        values.put(COL_3 , "VimanNagar");
        values.put(COL_4 , "9:00");
        values.put(COL_5 , "3.7");
        values.put(COL_6 , "15");
        values.put(COL_7 , "18.567929");
        values.put(COL_8 , "73.9143179");

        db.insert(TABLE_NAME, null, values);


        values.put(COL_1, "15");
        values.put(COL_2 , "LOHGAON");
        values.put(COL_3 , "Kothrud");
        values.put(COL_4 , "10:30");
        values.put(COL_5 , "10");
        values.put(COL_6 , "25");
        values.put(COL_7 , "18.5073985");
        values.put(COL_8 , "73.8076504");

        db.insert(TABLE_NAME, null, values);


        values.put(COL_1, "16");
        values.put(COL_2 , "LOHGAON");
        values.put(COL_3 , "Wagholi");
        values.put(COL_4 , "11:00");
        values.put(COL_5 , "10");
        values.put(COL_6 , "25");
        values.put(COL_7 , "18.579304999999998");
        values.put(COL_8 , "73.98234529999999");

        db.insert(TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
          onCreate(db);

    }



    public Cursor getAllData(String lot)

    {

       SQLiteDatabase db=this.getWritableDatabase();

       Cursor res=db.rawQuery("select * from " + TABLE_NAME + " WHERE " + COL_8 + " ='" +lot+ "'" ,null);

       return res;

    }


}
