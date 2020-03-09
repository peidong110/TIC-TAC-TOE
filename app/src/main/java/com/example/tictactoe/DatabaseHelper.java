package com.example.tictactoe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "board.db";
    public static final String TABLE_NAME =  "board_t";

    public static final String COL_1 =  "KEY_1";
    public static final String COL_2 =  "KEY_2";
    public static final String COL_3 =  "KEY_3";
    public static final String COL_4 =  "KEY_4";
    public static final String COL_5 =  "KEY_5";
    public static final String COL_6 =  "KEY_6";
    public static final String COL_7 =  "KEY_7";
    public static final String COL_8 =  "KEY_8";
    public static final String COL_9 =  "KEY_9";


    public DatabaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
