package com.example.eatwell.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import com.example.eatwell.DialogProgress;
import com.example.eatwell.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
   private Button btnLogin;
   private Button btnRegister;
   private EditText etEmail;
   private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail =findViewById(R.id.etEmail);
        etPassword= findViewById(R.id.etPassword);
        btnLogin= findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

//login to app
        btnLogin.setOnClickListener(v -> {
            if (!isEmailValid() | !isPasswordValid()) {
                return;
            }
            DialogProgress.getDialogProgress(true,this);

            FirebaseAuth mAuth;
            mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(getEmail(),getPassword())
                    .addOnSuccessListener(this,authResult -> {
                        DialogProgress.getDialogProgress(false,this);
                        Intent intent= new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }).addOnFailureListener(this,e -> {
                DialogProgress.getDialogProgress(false,this);
                new AlertDialog.Builder(this).setTitle(e.getMessage()).show();
            });
        });
//register to the app
        btnRegister.setOnClickListener(v -> {
            if (!isEmailValid() | !isPasswordValid()){
                return;
            }
            DialogProgress.getDialogProgress(true,this);
            FirebaseAuth mAuth;
            mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(getEmail(),getPassword())
                    .addOnSuccessListener(this,authResult -> {
                        DialogProgress.getDialogProgress(false,this);

                        Intent intent= new Intent(this,MainActivity.class);
                        startActivity(intent);
                        finish();

                    }).addOnFailureListener(this,e -> {

                DialogProgress.getDialogProgress(false,this);
                new AlertDialog.Builder(this).setTitle(e.getMessage()).show();
            });
        });

    }

    private String getEmail(){
            return etEmail.getText().toString();
    }
    private String getPassword(){
            return etPassword.getText().toString();
    }
    private boolean isEmailValid(){
        boolean isValid= Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
            if (!isValid){
                etEmail.setError("Email is not valid");
            }
            return isValid;
    }
    private boolean isPasswordValid(){
        boolean isValid= getPassword().length()>5;
        if (!isValid){
            etPassword.setError("Password must contain at least 6 characters");
        }
        return isValid;
    }
}