package com.metagain.frontend.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.metagain.frontend.R;

public class Homepage extends AppCompatActivity {

    ImageButton requests;
    ImageButton meetings;
    ImageButton profile;
    Button inRadius;

    Button buttonAddFriend;
    ImageButton contactProfile;

    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        requests = findViewById(R.id.homeToRequests);
        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRequests();
            }
        });

        meetings = findViewById(R.id.homeToMeetings);
        meetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMeetings();
            }
        });

        profile = findViewById(R.id.homeToProfile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserProfile();
            }
        });

        inRadius = findViewById(R.id.homeImRadius);
        inRadius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInRadius();
            }
        });

        contactProfile = findViewById(R.id.buttonToContactProfile);
        contactProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactProfile();
            }
        });

        buttonAddFriend = findViewById(R.id.buttonAddFriend);

        buttonAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFollowRequest();
            }
        });
    }

    public void openRequests() {
        Intent intent = new Intent(this, RequestsView.class);
        startActivity(intent);
    }

    public void openMeetings() {
        Intent intent = new Intent(this, MeetingsView.class);
        startActivity(intent);
    }

    public void openUserProfile() {
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    }

    public void openInRadius() {
        Intent intent = new Intent(this, HomepageInRadius.class);
        startActivity(intent);
    }

    public void openContactProfile() {
        Intent intent = new Intent(this, ContactProfile.class);
        startActivity(intent);
    }

    public void openFollowRequest() {
        Intent intent = new Intent(this, SendFollowRequest.class);
        startActivity(intent);
    }
}