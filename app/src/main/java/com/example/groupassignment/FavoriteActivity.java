package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    private String movie;

    ImageButton back;
    ListView favoritelist;
    Spinner sort;

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> name;
    ArrayList<String> sortList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        MovieData myMovieData = MovieData.getInstance();

        favoritelist = (ListView) findViewById(R.id.favlist);
        sort = (Spinner) findViewById(R.id.SortSpinner);
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


        sortList = new ArrayList<>();
        sortList.add(0,"Basic");
        sortList.add("Movie");
        sortList.add("Rating");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sortList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sort.setAdapter(dataAdapter);

        sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Basic"))
                {
                    name = myMovieData.listFavorite();
                    arrayAdapter = new ArrayAdapter<String>(FavoriteActivity.this, android.R.layout.simple_list_item_1, name);

                    favoritelist.setAdapter(arrayAdapter);
                }
                else if(adapterView.getItemAtPosition(i).equals("Movie")){
                    name = myMovieData.sortByMovie();
                    arrayAdapter = new ArrayAdapter<String>(FavoriteActivity.this, android.R.layout.simple_list_item_1, name);

                    favoritelist.setAdapter(arrayAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}