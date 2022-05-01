package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.groupassignment.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

    ArrayList<ReviewStorage> review;



    private String user;
    private String movie;
    private float averageReview;

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

        review = myReview.readReview(movie);


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
                    review = myReview.readReview(movie);
                    averageReview = myReview.amountOfStars(movie)/review.size();
                    myMovieData.addRate(String.valueOf(averageReview));
                    saveData(GiveReview.this, review);
                }
                else{
                    Toast.makeText(GiveReview.this, "Remember to give stars and write a comment!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveData(Context context, ArrayList<ReviewStorage> review){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(review);
        editor.putString("review list", json);
        editor.apply();
    }

}