package com.metagain.frontend.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.metagain.frontend.R;
import com.metagain.frontend.controll.FriendsController;
import com.metagain.frontend.controll.implementations.FriendsControllerImpl;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.Friends;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.services.LocationService;

import java.util.List;

public class Homepage extends AppCompatActivity {

    ImageButton requests;
    ImageButton meetings;
    ImageButton profile;
    Button inRadiusButton;

    Button allFriendsButton;
    Button addFriendButton;
    ImageButton contactProfile;

    FriendsController friendsController = new FriendsControllerImpl();

    List<Friends> friendsList;

    LinearLayout scrollView;



    boolean inRadius;

    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        if (!isServiceRunning(LocationService.class)) {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 123);
            Intent serviceIntent = new Intent(this, LocationService.class);
            startService(serviceIntent);
        }


        scrollView = findViewById(R.id.profileCardContainer);

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

        inRadiusButton = findViewById(R.id.homeImRadius);
        inRadiusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inRadius) {
                    scrollView.removeAllViews();
                    showFriendsInRadius();
                    createAllProfileCards();
                    inRadius = true;
                }
            }
        });


        allFriendsButton = findViewById(R.id.allFriends);

        allFriendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inRadius) {
                    scrollView.removeAllViews();
                    showAllFriends();
                    createAllProfileCards();
                    inRadius = false;
                }
            }
        });




        addFriendButton = findViewById(R.id.buttonAddFriend);

        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFollowRequest();
            }
        });

        showAllFriends();
        createAllProfileCards();


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



    public void openContactProfile(Friends friends) {
        Intent intent = new Intent(this, ContactProfile.class);

        intent.putExtra("friends", friends);

        startActivity(intent);
    }

    public void openFollowRequest() {
        Intent intent = new Intent(this, SendFollowRequest.class);
        startActivity(intent);
    }

    private void createProfileCard(Friends friend) {
        View cardViewLayout = getLayoutInflater().inflate(R.layout.friend_card_layout, null);

        TextView radiusTextView = cardViewLayout.findViewById(R.id.textViewRadiusFriend);
        TextView nameTextView = cardViewLayout.findViewById(R.id.textViewNameFriend);
        ImageButton toContactProfile = cardViewLayout.findViewById(R.id.buttonToContactProfile);

        toContactProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactProfile(friend);
            }
        });

        radiusTextView.setText("Radius: " + friend.getRadius() + "m");
        nameTextView.setText(friend.getFriendsProfile().getUsername());

        scrollView.addView(cardViewLayout);
    }

    public void showAllFriends() {
        try {
            friendsList = friendsController.getFriends();
        } catch (NetworkErrorException e) {
            Toast.makeText(Homepage.this, "Network Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void showFriendsInRadius() {
        try {
            friendsList = friendsController.getFriendsInRadius();

        } catch (NetworkErrorException e) {
            Toast.makeText(Homepage.this, "Network Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void createAllProfileCards() {
        for (Friends friend: friendsList) {
            createProfileCard(friend);
        }
    }

    private boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent serviceIntent = new Intent(this, LocationService.class);
        stopService(serviceIntent);
    }
}