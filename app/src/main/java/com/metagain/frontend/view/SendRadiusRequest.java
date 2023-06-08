package com.metagain.frontend.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.metagain.frontend.R;
import com.metagain.frontend.controll.RequestController;
import com.metagain.frontend.controll.implementations.RequestControllerImpl;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.NotFriendsException;
import com.metagain.frontend.model.Friends;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.model.types.RequestType;

public class SendRadiusRequest extends AppCompatActivity {

    TextView friendsUsername;

    Button sendRequest;

    EditText radiusInput;

    RequestController requestController = new RequestControllerImpl();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_radius_request);
        Intent intent = getIntent();
        Friends friends = (Friends) intent.getSerializableExtra("friends");

        friendsUsername = findViewById(R.id.textViewFriendsUsernameSendRadius);
        friendsUsername.setText(friends.getFriendsProfile().getUsername());

        radiusInput = findViewById(R.id.editTextRadius);

        sendRequest = findViewById(R.id.buttonSendRadiusRequest);
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request(friends.getFriendsProfile(), RequestType.RADIUS);

                try {
                    int radius = Integer.parseInt(radiusInput.getText().toString());
                    request.setRadius(radius);
                    requestController.sendRequest(request);

                } catch (NotFriendsException e) {
                    Toast.makeText(SendRadiusRequest.this, "No Friend!", Toast.LENGTH_SHORT).show();
                } catch (NetworkErrorException e) {
                    Toast.makeText(SendRadiusRequest.this, "Network Error!", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(SendRadiusRequest.this, "Invalid Input!", Toast.LENGTH_SHORT).show();
                }
                openHompage();
            }
        });

    }

    public void openHompage() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

}
