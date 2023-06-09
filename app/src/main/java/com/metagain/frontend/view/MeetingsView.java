package com.metagain.frontend.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.metagain.frontend.R;
import com.metagain.frontend.controll.MeetingController;
import com.metagain.frontend.controll.implementations.MeetingControllerImpl;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.handler.ActivityExceptionHandler;
import com.metagain.frontend.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingsView extends AppCompatActivity {

    ImageButton homeBack;

    MeetingController meetingController = new MeetingControllerImpl();

    LinearLayout parentLayout;

    ActivityExceptionHandler activityExceptionHandler = new ActivityExceptionHandler(this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);

        parentLayout = findViewById(R.id.cardContainerMeetings);


        homeBack = findViewById(R.id.imageMeetingsBack);
        homeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });

        List<Meeting> meetings = new ArrayList<>();

        try {
            meetings = meetingController.getMeetings();
        } catch (NetworkErrorException e) {
            activityExceptionHandler.handleNetworkErrorException();
        }
        if (meetings != null) {
            for (Meeting meeting : meetings) {
                createOneCard(meeting);
            }
        }
    }

    public void backToHome() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }



    public void openMap(Meeting meeting) {
        Intent intent = new Intent(this, MapActivity.class);

        intent.putExtra("meeting", meeting);

        startActivity(intent);
    }

    public void createOneCard(Meeting meeting) {
        View cardViewLayout = getLayoutInflater().inflate(R.layout.meeting_card, null);

        Button usernameButton = cardViewLayout.findViewById(R.id.buttonUsernameMeeting);
        usernameButton.setText(meeting.getProfile().getUsername());
        usernameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap(meeting);
            }
        });

        ImageButton deleteMeetingButton = cardViewLayout.findViewById(R.id.imageDeleteMeeting);
        deleteMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    meetingController.deleteMeeting(meeting.getId());
                    parentLayout.removeView(cardViewLayout);
                } catch (NetworkErrorException e) {
                    activityExceptionHandler.handleNetworkErrorException();
                }
            }
        });

        parentLayout.addView(cardViewLayout);
    }

    @Override
    public void onBackPressed() {

        backToHome();
    }
}