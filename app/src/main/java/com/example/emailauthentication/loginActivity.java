package com.example.emailauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class loginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    TextView signUp;
    EditText emailTxt, paswdTxt;
    Button loginBtn;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initializing mAuth
        mAuth = FirebaseAuth.getInstance();

        signUp = findViewById(R.id.signUpRedirect);
        emailTxt = findViewById(R.id.txtEmail);
        paswdTxt = findViewById(R.id.txtPassword);
        loading = findViewById(R.id.progress);
        loginBtn = findViewById(R.id.btnLogin);




        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);

                String email = String.valueOf(emailTxt.getText());
                String paswd = String.valueOf(paswdTxt.getText());

                if(TextUtils.isEmpty(email)){
                    toast("Enter email.");
                    return;
                }
                if(TextUtils.isEmpty(paswd)){
                    toast("Enter password");
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, paswd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                loading.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success,
                                    toast("Login success");
                                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    toast("Login Failed");
                                }
                            }
                        });
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, registrationActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null).
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(loginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    private void toast(String mag) {
        Toast.makeText(this, mag, Toast.LENGTH_SHORT).show();
    }
}