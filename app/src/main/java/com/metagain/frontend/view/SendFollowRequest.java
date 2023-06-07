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
import com.metagain.frontend.model.Profile;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.model.types.RequestType;

public class SendFollowRequest extends AppCompatActivity {

    private EditText etUserToFollow;

    private Button buttonSendRequest;

    private RequestController requestController = new RequestControllerImpl();

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
                Profile profile = new Profile(username);
                Request request = new Request(profile, RequestType.FOLLOW);
                try {
                    requestController.sendRequest(request);
                } catch (NotFriendsException e) {
                    //ignore
                    System.out.println("foo");
                } catch (NetworkErrorException e) {
                    Toast.makeText(SendFollowRequest.this, "Network Error", Toast.LENGTH_SHORT).show();
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
