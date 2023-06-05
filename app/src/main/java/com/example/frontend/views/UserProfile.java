package com.example.frontend.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.metagain.R;

public class UserProfile extends AppCompatActivity {

    ImageButton profileBack;
    Button abmelden;
    Button kontoLoeschen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profileBack = findViewById(R.id.imageProfileBack);
        profileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });

        abmelden = findViewById(R.id.buttonAbmelden);
        abmelden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }
        });

        kontoLoeschen = findViewById(R.id.buttonLoeschen);
        kontoLoeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }
        });
    }

    public void backToHome() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

    public void toLogin() {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
}