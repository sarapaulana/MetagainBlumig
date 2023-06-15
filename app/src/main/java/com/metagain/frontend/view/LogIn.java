package com.metagain.frontend.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TaskStackBuilder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.metagain.frontend.R;
import com.metagain.frontend.controll.ProfileController;
import com.metagain.frontend.controll.implementations.ProfileControllerImpl;
import com.metagain.frontend.exceptions.LoginException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.handler.ActivityExceptionHandler;

public class LogIn extends AppCompatActivity {

    private Button buttonLogIn;
    private Button buttonRegister;

    private EditText etUsername, etPassword;

    private ProfileController profileController = new ProfileControllerImpl();

    ActivityExceptionHandler activityExceptionHandler = new ActivityExceptionHandler(this);



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        etPassword = findViewById(R.id.editTextPassword);
        etUsername = findViewById(R.id.editTextUsername);

        buttonLogIn = findViewById(R.id.buttonLogin);
        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                try {
                    profileController.login(username, password);
                    openHomepage();
                } catch (LoginException e) {
                    activityExceptionHandler.handleLoginException();
                } catch (NetworkErrorException e) {
                    activityExceptionHandler.handleNetworkErrorException();
                }


            }
        });
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openToRegister();
            }
        });
    }

    public void openHomepage() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

    public void openToRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }




}