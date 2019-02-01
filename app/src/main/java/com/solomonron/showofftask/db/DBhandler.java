package com.solomonron.showofftask.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

public class DBhandler {

    private DBhelper helper;


    public DBhandler(Context context) {
        helper = new DBhelper(context, Constants.DATABASE_NAME, null,
                Constants.DATABASE_VERSION);
    }

    public void addMovie(Movie movie) {

        SQLiteDatabase db = null;

        try {


            db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(Constants.COLUMN_TITLE, movie.getTitle());
            values.put(Constants.COLUMN_URL, movie.getUrl());
            values.put(Constants.COLUMN_RATING, movie.getRating());
            values.put(Constants.COLUMN_RELEASE_YEAR, movie.getReleaseYear());
            values.put(Constants.COLUMN_GENRE, movie.getGenre());


            db.insert(Constants.TABLE_NAME, null, values);

        } catch (SQLiteException e) {

        } finally {
            if (db.isOpen())
                db.close();
        }
    }

    public ArrayList<Movie> getAllMovies() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {

            cursor = db.query(Constants.TABLE_NAME, null, null,
                    null, null, null, Constants.COLUMN_RELEASE_YEAR + " DESC");

        } catch (SQLiteException e) {
            e.getCause();
        }

        ArrayList<Movie> table = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String url = cursor.getString(2);
            double rating = cursor.getDouble(3);
            int releaseYear = cursor.getInt(4);
            String genre = cursor.getString(5);
            table.add(new Movie(id, title, url, rating, releaseYear, genre));

        }
        return table;
    }

    public Movie getMovie(String id1) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {

            cursor = db.query(Constants.TABLE_NAME, null, "_id=?",
                    new String[]{id1}, null, null, null);

        } catch (SQLiteException e) {
            e.getCause();
        }

        Movie table = null;

        if (cursor != null && cursor.moveToFirst()) {

            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String url = cursor.getString(2);
            double rating = cursor.getDouble(3);
            int releaseYear = cursor.getInt(4);
            String genre = cursor.getString(5);
            cursor.close();


            table = new Movie(id, title, url, rating, releaseYear, genre);

        }
        return table;


    }

    public void deleteAll() {

        SQLiteDatabase db = helper.getWritableDatabase();
        try {


            db.delete(Constants.TABLE_NAME, null, null);
        } catch (SQLiteException e) {

        } finally {
            if (db.isOpen())
                db.close();
        }


    }


}


