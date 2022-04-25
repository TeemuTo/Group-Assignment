package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

public class GiveReview extends AppCompatActivity {

    int rate;
    String commentText;

    ImageButton back;
    Button submit;
    RatingBar stars;
    EditText comment;
    ListView ratinglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_review);

        Review myReview = Review.getReviewInstance();

        back = (ImageButton) findViewById(R.id.review_back);
        submit = (Button) findViewById(R.id.submitrating);
        stars = (RatingBar) findViewById(R.id.ratingBar);
        comment = (EditText) findViewById(R.id.comment);
        ratinglist = (ListView) findViewById(R.id.ratinglist);

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
                rate = (int) v;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentText = comment.getText().toString();
                if(myReview.addReview("movie", "Teemu", commentText, rate)){
                    //rating is added
                    //Change visibility (only listview is visible)
                }
                else{
                    Toast.makeText(GiveReview.this, "Remember to give stars and write a comment!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}