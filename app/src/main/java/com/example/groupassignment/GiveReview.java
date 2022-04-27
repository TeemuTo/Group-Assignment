package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class GiveReview extends AppCompatActivity {

    private int rating;
    String commentText;

    ImageButton back;
    Button submit;
    RatingBar stars;
    EditText comment;
    ListView ratinglist;

    private ArrayList<String> reviews;
    private ArrayAdapter<String> list;
    private String user;
    private String movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_review);

        Review myReview = Review.getInstance();
        UserData myUserData = UserData.getInstance();
        MovieData myMovieData = MovieData.getInstance();
        user = myUserData.getUser();
        movie = myMovieData.getMovie();

        reviews = new ArrayList<>();
        reviews.add("Teemu");
        reviews.add("Henri");
        reviews.add("Jyri");
        reviews.add("Wenla");

        back = (ImageButton) findViewById(R.id.review_back);
        submit = (Button) findViewById(R.id.submitrating);
        stars = (RatingBar) findViewById(R.id.ratingBar);
        comment = (EditText) findViewById(R.id.comment);
        ratinglist = (ListView) findViewById(R.id.ratinglist);

        list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reviews);
        ratinglist.setAdapter(list);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //back to the movie
                startActivity(new Intent(GiveReview.this, HomeActivity.class));
            }
        });


        stars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rate = (int) v;
                myReview.setRate(rate);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = myReview.getRate();
                commentText = comment.getText().toString();
                if(myReview.addReview(movie, user, commentText, rating)){
                    //rating is added
                    //Change visibility (only listview is visible and the movie logo)
                }
                else{
                    Toast.makeText(GiveReview.this, "Remember to give stars and write a comment!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}