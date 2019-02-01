package com.solomonron.showofftask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


import com.solomonron.showofftask.db.DBhandler;
import com.solomonron.showofftask.db.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView RVList;
    private ArrayList<Movie> movieList = new ArrayList<>();
    private MovieListAdapter mAdapter;
    private DBhandler handler = new DBhandler(MainActivity.this);
    private boolean flag;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TaskGetMovieList mTaskGetMovieList = new TaskGetMovieList(MainActivity.this);
        String str = "https://api.androidhive.info/json/movies.json";
        mTaskGetMovieList.execute(str);

        RVList = findViewById(R.id.RVList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        RVList.setLayoutManager(layoutManager);

        mAdapter = new MovieListAdapter(this, movieList);
        RVList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


    }


    public class TaskGetMovieList extends AsyncTask<String, Void, String> {

        private Activity activity;
        private int id;

        public TaskGetMovieList(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected String doInBackground(String... strings) {
            return sendHttpRequest(strings[0]);
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            handler.deleteAll();


            if (s != null) {


                try {

                    String title = "";
                    String url = "";
                    double rating = 0;
                    int releaseYear = 0;
                    String genre = "";


                    JSONArray arr = new JSONArray(s);

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject item = arr.getJSONObject(i);
                        title = item.getString("title");
                        url = item.getString("image");
                        rating = item.getDouble("rating");
                        releaseYear = item.getInt("releaseYear");


                        if (!item.isNull("genre")) {
                            JSONArray genres = item.getJSONArray("genre");
                            genre = genres.getString(0);

                        }


                        handler.addMovie(new Movie(id, title, url, rating, releaseYear, genre));


                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                }

                movieList = handler.getAllMovies();
                mAdapter = new MovieListAdapter(MainActivity.this, movieList);

                RVList.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();


            }


        }

        private String sendHttpRequest(String urlString) {
            BufferedReader input = null;
            HttpURLConnection httpCon = null;
            InputStream input_stream = null;
            InputStreamReader input_stream_reader = null;
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL(urlString);
                httpCon = (HttpURLConnection) url.openConnection();
                if (httpCon.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.e("TAG", "Cannot Connect to : " + urlString);
                    return null;
                }

                input_stream = httpCon.getInputStream();
                input_stream_reader = new InputStreamReader(input_stream);
                input = new BufferedReader(input_stream_reader);
                String line;
                while ((line = input.readLine()) != null) {
                    response.append(line + "\n");
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input_stream_reader.close();
                        input_stream.close();
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (httpCon != null) {
                        httpCon.disconnect();
                    }
                }
            }
            return response.toString();
        }


    }
}
