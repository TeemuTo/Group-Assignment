package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    ListView searchlist;
    ImageButton settings;
    Toolbar toolbar;

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.HomeToolbar);
        setSupportActionBar(toolbar);

        name = new ArrayList<>();
        name.add("Teemu");
        name.add("Henri");
        name.add("Jyri");
        name.add("Wenla");


        searchlist = findViewById(R.id.searchlist);
        settings = (ImageButton) findViewById(R.id.settings);



        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
            }
        });

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);

        searchlist.setAdapter(arrayAdapter);

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
}