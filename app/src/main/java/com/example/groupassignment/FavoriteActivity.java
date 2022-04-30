package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    private String movie;

    ImageButton back;
    ListView favoritelist;

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        MovieData myMovieData = MovieData.getInstance();

        favoritelist = (ListView) findViewById(R.id.favlist);
        back = (ImageButton) findViewById(R.id.favorite_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavoriteActivity.this, HomeActivity.class));
            }
        });
        //do filter

        name = myMovieData.listFavorite();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);

        favoritelist.setAdapter(arrayAdapter);

        favoritelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                movie = name.get(i);
                myMovieData.setMovie(movie);
                startActivity(new Intent(FavoriteActivity.this, MovieActivity.class));
            }
        });
    }
}