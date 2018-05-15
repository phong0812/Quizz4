package com.example.truongthanh.quizz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Profile extends AppCompatActivity {
    private EditText inputEmail,inputnewpassword;
    private Button btnReset, btnBack, btnshownewpassword, btnchangepassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    TextView email,username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        email = findViewById(R.id.profile_email);
        username = findViewById(R.id.profile_username);
        inputnewpassword =findViewById(R.id.new_password);
        btnshownewpassword = findViewById(R.id.show_change_password);
        btnchangepassword = findViewById(R.id.change_password);

        auth=FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();
        email.setText("" +user.getEmail());

        btnshownewpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputnewpassword.setVisibility(View.VISIBLE);
                btnchangepassword.setVisibility(View.VISIBLE);
            }
        });

        btnchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePassword();
            }
        });



    }
    public void ChangePassword(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user !=null){
            user.updatePassword(inputnewpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Your password has been changed",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
