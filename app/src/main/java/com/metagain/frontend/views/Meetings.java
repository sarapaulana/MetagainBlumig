package com.metagain.frontend.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.metagain.R;

public class Meetings extends AppCompatActivity {

    ImageButton homeBack;
    ImageButton deleteMeeting;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);

        homeBack = findViewById(R.id.imageMeetingsBack);

        homeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });

        deleteMeeting = findViewById(R.id.imageDeleteMeeting);

        deleteMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDeclined();
            }
        });
    }

    public void backToHome() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

    public void toDeclined() {
        Intent intent = new Intent(this, Declined.class);
        startActivity(intent);
    }
}