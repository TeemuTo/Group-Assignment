package com.example.groupassignment;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {

    EditText addusername;
    EditText addpassword;
    MaterialButton register;
    String p, u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserData myUserData = UserData.getInstance();

        addusername = (EditText) findViewById(R.id.addusername);
        addpassword = (EditText) findViewById(R.id.addpassword);

        register = (MaterialButton) findViewById(R.id.adduser);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p=addpassword.getText().toString();
                u=addusername.getText().toString();
                if(myUserData.addUser(u, p)){
                    //Added user
                    //Go back to sign in interface
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }
                else{
                    //didn't succeed
                    Toast.makeText(RegisterActivity.this, "The password must be at least 8 characters. There must be at least one number and Capital letter.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}