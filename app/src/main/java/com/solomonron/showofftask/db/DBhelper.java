package com.solomonron.showofftask.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {


    public DBhelper(@Nullable Context context, @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{

            String cmd = "CREATE TABLE " + Constants.TABLE_NAME+
                    " ("+Constants.COLUMN_NAME_ID +" INTEGER PRIMARY KEY, "
                    +Constants.COLUMN_TITLE + " TEXT, " +
                    Constants.COLUMN_URL + " TEXT, " +
                    Constants.COLUMN_RATING + " TEXT, " +
                    Constants.COLUMN_RELEASE_YEAR + " TEXT, " +
                    Constants.COLUMN_GENRE + " TEXT);";

            db.execSQL(cmd);

        }catch (SQLException e){
            e.getCause();
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
