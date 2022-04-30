package com.example.groupassignment;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    EditText addusername;
    EditText addpassword;
    EditText city;
    EditText email;
    MaterialButton register;
    String p, u, c, e;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserData myUserData = UserData.getInstance();

        loadData();
        addusername = (EditText) findViewById(R.id.addusername);
        addpassword = (EditText) findViewById(R.id.addpassword);
        city = (EditText) findViewById(R.id.city);
        email = (EditText) findViewById(R.id.addmail);
        register = (MaterialButton) findViewById(R.id.adduser);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p=addpassword.getText().toString();
                u=addusername.getText().toString();
                c=city.getText().toString();
                e=email.getText().toString();
                try {
                    if(p.isEmpty() || u.isEmpty() || c.isEmpty() || e.isEmpty()){
                        Toast.makeText(RegisterActivity.this, "You must fill everything!", Toast.LENGTH_SHORT).show();
                    }
                    if(myUserData.addUser(u, p, c, e)){
                        //Added user
                        //Go back to sign in interface
                        saveData();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                    }
                    else{
                        //didn't succeed
                        Toast.makeText(RegisterActivity.this, "The password must be at least 8 characters. There must be at least one number and Capital letter.", Toast.LENGTH_SHORT).show();
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void saveData(){
        UserData myUserData = UserData.getInstance();
        ArrayList<User> users = myUserData.getUsers();

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(users);
        editor.putString("user list", json);
        editor.apply();
    }

    private void loadData(){
        UserData myUserData = UserData.getInstance();
        ArrayList<User> users = myUserData.getUsers();

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("user list", null);
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        users = gson.fromJson(json, type);

        if(users == null){
            users = new ArrayList<>();
        }
    }
}