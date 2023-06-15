package com.metagain.frontend.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.metagain.frontend.R;
import com.metagain.frontend.controll.RequestController;
import com.metagain.frontend.controll.implementations.RequestControllerImpl;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.NotFriendsException;
import com.metagain.frontend.exceptions.handler.ActivityExceptionHandler;
import com.metagain.frontend.model.Profile;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.model.types.RequestType;

public class SendFollowRequest extends AppCompatActivity {

    private EditText etUserToFollow;

    private Button buttonSendRequest;

    private RequestController requestController = new RequestControllerImpl();

    ActivityExceptionHandler activityExceptionHandler = new ActivityExceptionHandler(this);

    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_follow_request);
        etUserToFollow = findViewById(R.id.editTextFollowUser);
        buttonSendRequest = findViewById(R.id.buttonSendRequest);

        buttonSendRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = etUserToFollow.getText().toString();
                try {
                    requestController.sendFollowRequest(username);
                } catch (NetworkErrorException e) {
                    activityExceptionHandler.handleNetworkErrorException();
                }
                backToHome();
            }
        });

    }

    public void backToHome() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

}
