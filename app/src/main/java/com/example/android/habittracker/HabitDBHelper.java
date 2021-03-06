package com.example.android.habittracker;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android.habittracker.HabitTrackerContract.HabitContract.COLUMN_NAME;
import static com.example.android.habittracker.HabitTrackerContract.HabitContract.COLUMN_SLEPT_FOR;
import static com.example.android.habittracker.HabitTrackerContract.HabitContract.TABLE_NAME;
import static com.example.android.habittracker.HabitTrackerContract.HabitContract.COLUMN_EXERCISE;
import static com.example.android.habittracker.HabitTrackerContract.HabitContract.COLUMN_ID;
import static com.example.android.habittracker.HabitTrackerContract.HabitContract.COLUMN_NAME;
import static com.example.android.habittracker.HabitTrackerContract.HabitContract.COLUMN_SLEPT_FOR;
import static com.example.android.habittracker.HabitTrackerContract.HabitContract.TABLE_NAME;

/**
 * Created by Sonal on 3/22/2017.
 */

public class HabitDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDBHelper.class.getSimpleName();

    static final String DATABASE_NAME = "Habit.db";
    static final int DATABASE_VERSION = 1;

    public HabitDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL, "
                + COLUMN_EXERCISE + " INTEGER DEFAULT 0, "
                + COLUMN_SLEPT_FOR + " INTEGER NOT NULL DEFAULT 7);";
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}