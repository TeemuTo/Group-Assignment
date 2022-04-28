package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MovieActivity extends AppCompatActivity {

    ImageButton back;
    ImageButton like;
    MaterialButton toReview;
    TextView title;
    ImageView logo;
    TextView duration;
    TextView IMDB;

    int nro = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        MovieData myMovieData = MovieData.getInstance();

        back = (ImageButton) findViewById(R.id.movie_back);
        like = (ImageButton) findViewById(R.id.movie_like);
        toReview = (MaterialButton) findViewById(R.id.toReview);
        title = (TextView) findViewById(R.id.MovieTitle);
        logo = (ImageView) findViewById(R.id.MovieLogo);
        duration = (TextView) findViewById(R.id.MovieDuration);
        IMDB = (TextView) findViewById(R.id.IMDBReview);

        //setText by every movie
        //image is setImageResource

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MovieActivity.this, HomeActivity.class));

            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(nro%2==0) {
                   like.setImageResource(R.drawable.ic_movie_liked);
                   nro++;
               }
               else{
                   like.setImageResource((R.drawable.ic_movie_like));
                   nro++;
               }
            }
        });

        toReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MovieActivity.this, GiveReview.class));
            }
        });


    }
}