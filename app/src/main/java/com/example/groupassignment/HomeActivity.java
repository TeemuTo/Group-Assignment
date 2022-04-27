package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    ListView searchlist;
    ImageButton settings;
    ImageButton favorite;
    Toolbar toolbar;
    Spinner teatterispinner;

    private String movie;

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MovieData myMovieData = MovieData.getInstance();

        teatterispinner = findViewById(R.id.teatterispinner);
        List<String> teatterit = new ArrayList<>();
        teatterit.add(0,"Valitse teatteri");
        teatterit.add("KINOPALATSI");
        teatterit.add("Espoo");
        teatterit.add("OMENA");
        teatterit.add("SELLO");
        teatterit.add("PLAZA");
        teatterit.add("ITIS");
        teatterit.add("KINOPALATSI");
        teatterit.add("MAXIM");
        teatterit.add("TENNISPALATSI");
        teatterit.add("FLAMINGO");
        teatterit.add("FANTASIA");
        teatterit.add("SCALA");
        teatterit.add("KUVAPALATSI");
        teatterit.add("STRAND");
        teatterit.add("PROMENADI");
        teatterit.add("CINE ATLAS");
        teatterit.add("PLEVNA");




        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, teatterit);

        //layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //liitetään dataadapter spinneriin
        teatterispinner.setAdapter(dataAdapter);

        teatterispinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Valitse teatteri"))
                {

                }
                else{
                    String item = adapterView.getItemAtPosition(i).toString();

                    Toast.makeText(HomeActivity.this, "Valittu: " +item, Toast.LENGTH_SHORT).show();
                    //jos haluaa tehdä muuta valinnassa niin tee tähän
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebview();
            }
        });
        toolbar = (Toolbar) findViewById(R.id.HomeToolbar);
        setSupportActionBar(toolbar);

        name = new ArrayList<>();
        name.add("Teemu");
        name.add("Henri");
        name.add("Jyri");
        name.add("Wenla");


        searchlist = findViewById(R.id.searchlist);
        settings = (ImageButton) findViewById(R.id.settings);
        favorite = (ImageButton) findViewById(R.id.Favorite);


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
            }
        });

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);

        searchlist.setAdapter(arrayAdapter);

        searchlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                movie = name.get(i);
                myMovieData.setMovie(movie);
                startActivity(new Intent(HomeActivity.this, GiveReview.class));
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to favoritelist
                startActivity(new Intent(HomeActivity.this, FavoriteActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        MenuItem menuitem = menu.findItem(R.id.search);
        SearchView searchview = (SearchView) menuitem.getActionView();
        searchview.setQueryHint("Type here to search movies");

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                if(newText.isEmpty()){
                    searchlist.setVisibility(View.GONE);
                }
                else{
                    searchlist.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        searchlist.setVisibility(View.GONE);
        return super.onCreateOptionsMenu(menu);
    }
    public void openWebview(){
        Intent intent = new Intent(this, Webview.class);
        startActivity(intent);
    }
}