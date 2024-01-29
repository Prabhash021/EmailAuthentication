package com.example.emailauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registrationActivity extends AppCompatActivity {

    EditText email, paswd;
    Button registerBtn;
    ProgressBar loading;
    TextView loginTxt;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance(); //initializing mAuth

        email = findViewById(R.id.txtEmail);
        paswd = findViewById(R.id.txtPassword);
        loading = findViewById(R.id.progress);
        loginTxt = findViewById(R.id.loginRedirect);
        registerBtn = findViewById(R.id.btnRegister);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);

                String sEmail = String.valueOf(email.getText());
                String sPaswd = String.valueOf(paswd.getText());

                if (TextUtils.isEmpty(sEmail)) {
                    toast("Enter email.");  //Checks empty field
                    return;
                }

                if (TextUtils.isEmpty(sPaswd)) {
                    toast("Enter Password");    //Checks empty field
                    return;
                }

                mAuth.createUserWithEmailAndPassword(sEmail, sPaswd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                loading.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success,
                                    toast("Registration Successful");
                                } else {
                                    // If sign in fails,
                                    toast("Registration failed");
                                }
                            }
                        });
            }
        });

        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //Redirects towards Login Activity
                Intent intent = new Intent(registrationActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null).
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(registrationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            startActivity(new Intent(registrationActivity.this, registrationActivity.class));
        }
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}