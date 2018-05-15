package com.example.truongthanh.quizz;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.truongthanh.quizz.R;
import  com.example.truongthanh.quizz.User;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText inputEmail, inputPassword, inputUsername;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private User user;
    private FirebaseDatabase database;

    private DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();


        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        inputEmail = (EditText) findViewById(R.id.signup_user);
        inputPassword = (EditText) findViewById(R.id.signup_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        inputUsername = findViewById(R.id.signup_username);
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference();

        btnSignUp.setOnClickListener(this);




    }
    @Override
    public void onClick(View view) {
        if (view == btnSignUp) {
            register();
            finish();

        }
    }
    public void register(){

        final String username = inputUsername.getText().toString().trim();
        final String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
//                              if(mfirebaseAuth.getCurrentUser() != null){
//                               userID = mfirebaseAuth.getCurrentUser().getUid();
//
//                              }
                            Toast.makeText(SignUp.this,"Registered Successfully",Toast.LENGTH_LONG).show();

                        }else {

                            Toast.makeText(SignUp.this,"Could not register, Please try again !",Toast.LENGTH_LONG).show();
                        }
                    }
                });
        user=new User(username,email);
        mRef.child("users").child(email.substring(0,email.indexOf("@"))).setValue(user);



    }


    /*
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }*/
}

