package com.example.groupassignment;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.security.NoSuchAlgorithmException;

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
}