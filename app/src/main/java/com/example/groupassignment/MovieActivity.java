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
    TextView story;

    private String movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        MovieData myMovieData = MovieData.getInstance();

        movie = myMovieData.getMovie();


        back = (ImageButton) findViewById(R.id.movie_back);
        like = (ImageButton) findViewById(R.id.movie_like);
        toReview = (MaterialButton) findViewById(R.id.toReview);
        title = (TextView) findViewById(R.id.MovieTitle);
        logo = (ImageView) findViewById(R.id.MovieLogo);
        duration = (TextView) findViewById(R.id.MovieDuration);
        IMDB = (TextView) findViewById(R.id.IMDBReview);
        story = (TextView) findViewById(R.id.MovieStory);

        //setText by every movie
        //image is setImageResource
        title.setText(movie);
        duration.setText(myMovieData.getTime());
        IMDB.setText(myMovieData.getimdb());
        story.setText(myMovieData.getSummary());

        if(myMovieData.getImage()){
            like.setImageResource(R.drawable.ic_movie_like);
        }
        else{
            like.setImageResource((R.drawable.ic_movie_liked));
        }


        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myMovieData.getImage()) {
                    myMovieData.setLiked();
                    myMovieData.addFavorite(movie);
                    like.setImageResource((R.drawable.ic_movie_liked));
                }
                else{
                    myMovieData.setLike();
                    myMovieData.removeFavorite(movie);
                    like.setImageResource(R.drawable.ic_movie_like);
                }
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MovieActivity.this, HomeActivity.class));

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