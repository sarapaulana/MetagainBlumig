package com.metagain.frontend.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.metagain.frontend.R;
import com.metagain.frontend.controll.ProfileController;
import com.metagain.frontend.controll.implementations.ProfileControllerImpl;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.storage.ProfileDataStorage;

public class UserProfile extends AppCompatActivity {


    boolean edited;
    ImageButton profileBack;
    Button abmelden;
    Button kontoLoeschen;

    ProfileController profileController = new ProfileControllerImpl();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        View usernameView = getLayoutInflater().inflate(R.layout.text_view_username, null);
        View emailView = getLayoutInflater().inflate(R.layout.text_view_email, null);
        View passwordView = getLayoutInflater().inflate(R.layout.text_view_password,  null);

        LinearLayout linearLayoutUsername = findViewById(R.id.linearLayoutUsernameCard);
        TextView usernameText = usernameView.findViewById(R.id.textRealUsername);
        usernameText.setText(ProfileDataStorage.getUsername());

        LinearLayout linearLayoutEmail = findViewById(R.id.linearLayoutEmailCard);
        TextView emailText = emailView.findViewById(R.id.textRealEmail);
        emailText.setText(ProfileDataStorage.getEmail());

        LinearLayout linearLayoutPassword = findViewById(R.id.linearLayoutPasswordCard);
        TextView passwordText = passwordView.findViewById(R.id.textRealPassword);
        passwordText.setText(ProfileDataStorage.getPassword());

        linearLayoutUsername.addView(usernameView);
        linearLayoutEmail.addView(emailView);
        linearLayoutPassword.addView(passwordView);


        profileBack = findViewById(R.id.imageProfileBack);
        profileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });

        abmelden = findViewById(R.id.buttonAbmelden);
        abmelden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                profileController.logout();
                toLogin();
            }
        });

        kontoLoeschen = findViewById(R.id.buttonLoeschen);
        kontoLoeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    profileController.delete();
                } catch (NetworkErrorException e) {
                    Toast.makeText(UserProfile.this, "Network Error", Toast.LENGTH_SHORT).show();
                }
                toLogin();
            }
        });
    }

    public void backToHome() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

    public void toLogin() {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
}