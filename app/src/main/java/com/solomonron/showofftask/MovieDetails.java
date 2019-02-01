package com.solomonron.showofftask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.solomonron.showofftask.db.DBhandler;
import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

    private TextView title;
    private EditText rating;
    private EditText year;
    private TextView genre;
    private ImageView image;
    private int id;


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MovieDetails.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        overridePendingTransition(R.anim.act_transition_slide_in, R.anim.act_transition_slide_out);

        DBhandler handler = new DBhandler(MovieDetails.this);

        id = getIntent().getIntExtra("id", -1);

        title = findViewById(R.id.title_detail_TV);
        rating = findViewById(R.id.rating_ET);
        year = findViewById(R.id.year_test_ET);
        image = findViewById(R.id.image_detail_IV);
        genre = findViewById(R.id.genre_num_TV);


        if (getIntent().hasExtra("id")) {
            title.setText(handler.getMovie(id + "").getTitle() + "");
            rating.setText(String.valueOf(handler.getMovie(id + "").getRating()));
            year.setText(String.valueOf(handler.getMovie(id + "").getReleaseYear()));
            Picasso.get().load(handler.getMovie(id + "").getUrl()).fit().centerInside().into(image);
            genre.setText(handler.getMovie(id + "").getGenre());
        }


    }
}
