package com.example.groupassignment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton back;
    MaterialButton logout;
    TextView showUser;

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_settings);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));*/

        Button changeLanguage = findViewById(R.id.changeLang);
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show list of languages
                showChangeLanguageDialog();
            }
        });

        UserData myUserData = UserData.getInstance();

        showUser = (TextView) findViewById(R.id.showUser);
        back = (ImageButton) findViewById(R.id.settings_back);
        back.setOnClickListener(this);
        logout = (MaterialButton) findViewById(R.id.logout);
        logout.setOnClickListener(this);

        user = myUserData.getUser();
        showUser.setText(user);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings_back:
                startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
                break;

            case R.id.logout:
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                break;

            default:
                break;
        }
    }
    private void showChangeLanguageDialog() {
        final String[] listItems = {"English", "Finnish"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsActivity.this);
        mBuilder.setTitle("Choose language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    setLocale("Eng");
                    recreate();
                }
                else if(i==1){
                    setLocale("fi");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        //show alert dialog
        mDialog.show();

    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_lang", lang);
        editor.apply();

    }
    public void  loadLocale(){
        SharedPreferences pref = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = pref.getString("My_lang", "")           ;
        setLocale(language);
    }
}