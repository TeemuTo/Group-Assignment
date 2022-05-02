package com.example.groupassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
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

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class HomeActivity extends AppCompatActivity {

    ListView searchlist;
    ImageButton settings;
    ImageButton favorite;
    Toolbar toolbar;
    Spinner teatterispinner;

    private String movie;

    private final OkHttpClient client = new OkHttpClient();
    final String TAG = "kokeilu";

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getShow();

        MovieData myMovieData = MovieData.getInstance();

        teatterispinner = findViewById(R.id.teatterispinner);
        List<String> teatterit = new ArrayList<>();
        teatterit.add(0,"Choose Theatre");
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
                if (adapterView.getItemAtPosition(i).equals("Choose Theatre"))
                {

                }
                else{
                    String item = adapterView.getItemAtPosition(i).toString();

                    Toast.makeText(HomeActivity.this, "You Chose: " +item, Toast.LENGTH_SHORT).show();
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




        searchlist = findViewById(R.id.searchlist);
        settings = (ImageButton) findViewById(R.id.settings);
        favorite = (ImageButton) findViewById(R.id.Favorite);


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
            }
        });

        name = myMovieData.searchMovies();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);

        searchlist.setAdapter(arrayAdapter);

        searchlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                myMovieData.setMovie(item);
                startActivity(new Intent(HomeActivity.this, MovieActivity.class));
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

    void getShow(){

        Request request = new Request.Builder()
                .url("https://www.finnkino.fi/xml/Schedule/?area=1018&dt=01.05.2022")
                        .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, "onResponse: " + Thread.currentThread().getId());

                if (response.isSuccessful()) {



                    try {
                        ShowXMLParser showXMLParser = new ShowXMLParser();
                        ArrayList<Show> shows = showXMLParser.parse(response.body().byteStream());
                        Log.d(TAG, "onResponse: "+shows);
                    } catch (SAXException e){
                        e.printStackTrace();
                    }




                }
                else {
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    Log.d(TAG, "onResponse: " + body);

                }
            }
        });

    }
}