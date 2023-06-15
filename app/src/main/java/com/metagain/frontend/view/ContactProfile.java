package com.metagain.frontend.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.metagain.frontend.R;
import com.metagain.frontend.controll.FriendsController;
import com.metagain.frontend.controll.RequestController;
import com.metagain.frontend.controll.implementations.FriendsControllerImpl;
import com.metagain.frontend.controll.implementations.RequestControllerImpl;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.NotFriendsException;
import com.metagain.frontend.exceptions.handler.ActivityExceptionHandler;
import com.metagain.frontend.model.Friends;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.model.types.RequestType;

public class ContactProfile extends AppCompatActivity {

    ImageButton contactBack;

    Button unfollowButton;

    Button editRadius;

    Button requestMeeting;

    TextView friendsFullName;

    TextView friendsUsername;

    TextView friendsRadius;

    RequestController requestController = new RequestControllerImpl();

    FriendsController friendsController = new FriendsControllerImpl();

    ActivityExceptionHandler activityExceptionHandler = new ActivityExceptionHandler(this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        Friends friends = (Friends) intent.getSerializableExtra("friends");
        if(friends.isInRadius()) {
            setContentView(R.layout.activity_contact_profile_in_radius);
            requestMeeting = findViewById(R.id.buttonRequestMeeting);
            requestMeeting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        requestController.sendMeetingRequest(friends.getFriendsProfile());
                        backToHome();
                    } catch (NotFriendsException e) {
                        activityExceptionHandler.handleNotFriendsExcpetion();
                    } catch (NetworkErrorException e) {
                        activityExceptionHandler.handleNetworkErrorException();
                    }
                }
            });
        } else {
            setContentView(R.layout.activity_contact_profile);
        }


        friendsFullName = findViewById(R.id.textViewFriendsFullName);
        friendsFullName.setText(friends.getFriendsProfile().getFirstName() + " " + friends.getFriendsProfile().getLastName());

        friendsUsername = findViewById(R.id.textViewFriendsUsernameContact);
        friendsUsername.setText(friends.getFriendsProfile().getUsername());

        friendsRadius = findViewById(R.id.textViewFriendsRadiusContactProfile);
        friendsRadius.setText("YOUR RADIUS: " + friends.getRadius() + "m");

        unfollowButton = findViewById(R.id.buttonUnfollow);
        unfollowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    friendsController.deleteFriend(friends.getId());
                    backToHome();
                } catch (NetworkErrorException e) {
                    activityExceptionHandler.handleNetworkErrorException();
                }
            }
        });

        contactBack = findViewById(R.id.imageContactBack);
        contactBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });

        editRadius = findViewById(R.id.buttonEditRadius);
        editRadius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRadiusRequest(friends);
            }
        });


    }

    public void backToHome() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

    public void openRadiusRequest(Friends friends) {
        Intent intent = new Intent(this, SendRadiusRequest.class);

        intent.putExtra("friends", friends);

        startActivity(intent);

    }
}