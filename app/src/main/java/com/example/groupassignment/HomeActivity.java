package com.example.groupassignment;
/*<androidx.appcompat.widget.Toolbar
        android:id="@+id/HomeToolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/yellow"
        android:minHeight="?attr/actionBarSize"
        android:theme="?attr/actionBarTheme"
        app:layout_constraintTop_toTopOf="parent"
        app:navigationIcon="@drawable/ic_home_menu"
        tools:layout_editor_absoluteX="1dp"
        app:menu="@menu/menu"/>*/
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {

    ListView searchlist;
    String[] name = {"teemu", "henri", "jyri", "wenla"};

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        searchlist = findViewById(R.id.searchlist);

        arrayAdapter = new ArrayAdapter<>(HomeActivity.this, android.R.layout.simple_list_item_1, name);

        searchlist.setAdapter(arrayAdapter);

        searchlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), arrayAdapter.getItem(i), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuitem = menu.findItem(R.id.action_search);
        SearchView searchview = (SearchView) MenuItemCompat.getActionView(menuitem);
        searchview.setQueryHint("Type here to search theatres and movies");

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newtext) {

                arrayAdapter.getFilter().filter(newtext);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}