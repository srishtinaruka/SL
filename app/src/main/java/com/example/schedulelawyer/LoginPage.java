package com.example.schedulelawyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {
    EditText email;
    EditText password;
    Button loginButton;
    FirebaseAuth mauth;
    FirebaseUser user;
    TextView signUp;
    TextView forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        mauth=FirebaseAuth.getInstance();
        loginButton=findViewById(R.id.loginButton);
        signUp=findViewById(R.id.signUp);
        forgotPassword=findViewById(R.id.forgotPassword);
        if(user==null) {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    mauth.signInWithEmailAndPassword(
                            email.getText().toString(),
                            password.getText().toString()
                    ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LoginPage.this, HomePage.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginPage.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }else{
            Intent intent = new Intent(LoginPage.this, HomePage.class);
            startActivity(intent);
        }
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, SignUp.class);
                startActivity(intent);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, forgot_password.class);
                startActivity(intent);
            }
        });
    }
}