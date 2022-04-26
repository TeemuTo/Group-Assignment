package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton back;
    MaterialButton logout;
    TextView showUser;

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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
}