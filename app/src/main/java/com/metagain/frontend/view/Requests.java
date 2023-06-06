package com.metagain.frontend.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.metagain.frontend.R;

public class Requests extends AppCompatActivity {

    ImageButton alertsBack;
    ImageButton declineMeeting;

    ImageButton acceptMeeting;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        alertsBack = findViewById(R.id.imageAlertsBack);

        alertsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });

        declineMeeting = findViewById(R.id.imageDeclineMeeting);

        declineMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeclined();
            }
        });

        acceptMeeting = findViewById(R.id.imageAcceptMeeting);

        acceptMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMeetings();
            }
        });
    }

    public void backToHome() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

    public void openDeclined() {
        Intent intent = new Intent(this, Declined.class);
        startActivity(intent);
    }

    public void openMeetings() {
        Intent intent = new Intent(this, Meetings.class);
        startActivity(intent);
    }
}