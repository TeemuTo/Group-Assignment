package com.example.groupassignment;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupassignment.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView authstatus;
    private Button biobutton;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    EditText username;
    EditText password;
    String p, u;

    ArrayList<User> list;
    ArrayList<User> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authstatus = findViewById(R.id.authstatus);
        biobutton = findViewById(R.id.biobutton);
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                //error authentication, stop task
                super.onAuthenticationError(errorCode, errString);
                authstatus.setText("Authentication error: "+ errString);
                Toast.makeText(MainActivity.this, "Authentication error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //authentication succeed
                authstatus.setText("Authentication succeed");
                Toast.makeText(MainActivity.this, "Authentication succeed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                //failed auth...
                authstatus.setText("Authentication failed");
                Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Login using fingerprint")
                .setNegativeButtonText("Use App password")
                .build();

        biobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(promptInfo);
            }
        });


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        MaterialButton login = (MaterialButton) findViewById(R.id.loginbutton);
        login.setOnClickListener(this);
        MaterialButton register = (MaterialButton) findViewById(R.id.registerbutton);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        list = loadData(this);

        UserData myUserData = UserData.getInstance();
        user = myUserData.getUsers();
        if(user.isEmpty()){
            myUserData.addUserList(list);
        }

        switch (view.getId()) {
            case R.id.loginbutton:
                //finduser
                p=password.getText().toString();
                u=username.getText().toString();

                try {
                    if(myUserData.findUser(u,p)){
                        myUserData.setUser(u);
                        //home interface
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Username or password is incorrect!", Toast.LENGTH_SHORT).show();
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.registerbutton:
                //Interface for registration
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
            default:
                break;
        }
    }
    private ArrayList<User> loadData(Context context){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("user list", null);
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        ArrayList<User> u = gson.fromJson(json, type);

        if (u == null)
            u = new ArrayList<>();

        return u;
    }
}