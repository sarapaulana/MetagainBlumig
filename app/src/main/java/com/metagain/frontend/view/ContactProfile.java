package com.metagain.frontend.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.metagain.frontend.R;
import com.metagain.frontend.controll.FriendsController;
import com.metagain.frontend.controll.RequestController;
import com.metagain.frontend.controll.implementations.FriendsControllerImpl;
import com.metagain.frontend.controll.implementations.RequestControllerImpl;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.NotFriendsException;
import com.metagain.frontend.model.Friends;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.model.types.RequestType;

public class ContactProfile extends AppCompatActivity {

    ImageButton contactBack;

    Button unfollowButton;

    RequestController requestController = new RequestControllerImpl();

    FriendsController friendsController = new FriendsControllerImpl();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_profile);

        Intent intent = getIntent();
        Friends friends = (Friends) intent.getSerializableExtra("friends");

        unfollowButton = findViewById(R.id.buttonUnfollow);

        unfollowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    friendsController.deleteFriend(friends.getId());
                    backToHome();
                } catch (NetworkErrorException e) {
                    Toast.makeText(ContactProfile.this, "Network Error", Toast.LENGTH_SHORT).show();
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
    }

    public void backToHome() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }
}