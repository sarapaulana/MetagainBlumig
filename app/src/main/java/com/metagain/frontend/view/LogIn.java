package com.metagain.frontend.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class LogIn extends AppCompatActivity {

    private Button buttonLogIn;
    private Button buttonRegister;

    private EditText etUsername, etPassword;

    private ProfileController profileController = new ProfileControllerImpl();



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
                    Toast.makeText(LogIn.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                } catch (NetworkErrorException e) {
                    Toast.makeText(LogIn.this, "Network Error", Toast.LENGTH_SHORT).show();
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
}