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

import com.example.groupassignment.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class GiveReview extends AppCompatActivity {

    ActivityMainBinding binding;

    String rating;
    String commentText;

    ImageButton back;
    Button submit;
    RatingBar stars;
    EditText comment;
    ListView ratinglist;

    ArrayList<String> writer, rev;
    ArrayList<Integer> star;


    private String user;
    private String movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_give_review);

        Review myReview = Review.getInstance();
        UserData myUserData = UserData.getInstance();
        MovieData myMovieData = MovieData.getInstance();
        user = myUserData.getUser();
        movie = myMovieData.getMovie();


        ArrayList<ReviewStorage> review= myReview.readReview(movie);



        back = (ImageButton) findViewById(R.id.review_back);
        submit = (Button) findViewById(R.id.submitrating);
        stars = (RatingBar) findViewById(R.id.ratingBar);
        comment = (EditText) findViewById(R.id.comment);
        ratinglist = (ListView) findViewById(R.id.ratinglist);

        ReviewList reviews = new ReviewList(this, review);

        ratinglist.setAdapter(reviews);

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
                String rate = String.valueOf((int) v);
                myReview.setRate(rate);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = (String) myReview.getRate();
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