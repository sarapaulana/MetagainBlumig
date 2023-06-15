package com.metagain.frontend.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.metagain.frontend.R;
import com.metagain.frontend.controll.RequestController;
import com.metagain.frontend.controll.implementations.RequestControllerImpl;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.handler.ActivityExceptionHandler;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.model.types.RequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestsView extends AppCompatActivity {

    ImageButton alertsBack;

    RequestController requestController = new RequestControllerImpl();

    LinearLayout parentLayout;

    ActivityExceptionHandler activityExceptionHandler = new ActivityExceptionHandler(this);



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);



        parentLayout = findViewById(R.id.cardContainer);

        alertsBack = findViewById(R.id.imageAlertsBack);

        alertsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });



        List<Request> requestList = new ArrayList<>();

        try {
            requestList = requestController.getRequests();

        } catch (NetworkErrorException e) {
            activityExceptionHandler.handleNetworkErrorException();
        }

        for (Request request: requestList) {
            createOneCard(request);
        }
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
        Intent intent = new Intent(this, MeetingsView.class);
        startActivity(intent);
    }

    public void createOneCard(Request request) {
        String cardText = "ERROR";

        if (request.getRequestType().equals(RequestType.FOLLOW)) {
            cardText = request.getProfile().getUsername() + " möchte dir folgen";
        } else if (request.getRequestType().equals(RequestType.MEET)) {
            cardText = request.getProfile().getUsername() + " möchte sich mit dir treffen";
        } else if (request.getRequestType().equals(RequestType.RADIUS)) {
            cardText = request.getProfile().getUsername() + " möchte den radius auf " + request.getRadius() + "m ändern.";
        }

        View cardViewLayout = getLayoutInflater().inflate(R.layout.card_layout, null);

        TextView cardTextView = cardViewLayout.findViewById(R.id.cardText);
        ImageButton imageDecline = cardViewLayout.findViewById(R.id.imageDecline);

        imageDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    requestController.deleteRequest(request.getId());
                    parentLayout.removeView(cardViewLayout);
                } catch (NetworkErrorException e) {
                    activityExceptionHandler.handleNetworkErrorException();
                }
            }
        });

        ImageButton imageAccept = cardViewLayout.findViewById(R.id.imageAccept);

        imageAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    requestController.acceptRequest(request.getId());
                    parentLayout.removeView(cardViewLayout);
                } catch (NetworkErrorException e) {
                    activityExceptionHandler.handleNetworkErrorException();
                }
            }
        });

        cardTextView.setText(cardText);

        parentLayout.addView(cardViewLayout);
    }

}