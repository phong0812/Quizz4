package com.example.truongthanh.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {
    Button signout, profile;
    private FirebaseAuth auth;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        auth=FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();
        welcome = findViewById(R.id.welcome_user);
        welcome.setText("Welcome " +user.getEmail());

        signout = findViewById(R.id.btnsignout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(HomePage.this,MainActivity.class);
                startActivity(intent);

            }
        });

        profile = findViewById(R.id.btn_link_to_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,Profile.class);
                startActivity(intent);

            }
        });

    }
}
