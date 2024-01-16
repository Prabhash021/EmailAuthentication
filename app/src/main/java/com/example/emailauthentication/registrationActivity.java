package com.example.emailauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class registrationActivity extends AppCompatActivity {

    EditText email, paswd;
    Button loginbtn;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        email = findViewById(R.id.txtEmail);
        paswd = findViewById(R.id.txtPassword);
        loading = findViewById(R.id.progress);



    }
}