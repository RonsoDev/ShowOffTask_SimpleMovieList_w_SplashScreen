package com.solomonron.showofftask;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.solomonron.showofftask.db.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private Context mContext;
    private List<Movie> mMovieList;

    public MovieListAdapter(Context context, List<Movie> movieList) {
        mContext = context;
        mMovieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_new, parent, false);

        MovieViewHolder holder = new MovieViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {

        Movie movie = mMovieList.get(position);

        String image = movie.getUrl();
        String title = movie.getTitle();
        int release = movie.getReleaseYear();
        holder.title.setText(title);
        holder.release.setText(String.valueOf(release));
        Picasso.get().load(image).fit().centerInside().into(holder.image);

        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, MovieDetails.class);
                intent.putExtra("id", mMovieList.get(position).getId());
                mContext.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout mConstraintLayout;
        public ImageView image;
        public TextView title;
        public EditText release;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            mConstraintLayout = itemView.findViewById(R.id.constraint_new);
            image = itemView.findViewById(R.id.image_IV_new);
            title = itemView.findViewById(R.id.titleTV_new);
            release = itemView.findViewById(R.id.editTest);


        }
    }
}
