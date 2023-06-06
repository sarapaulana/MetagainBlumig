package com.metagain.frontend.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.metagain.frontend.R;
import com.metagain.frontend.controll.ProfileController;
import com.metagain.frontend.controll.implementations.ProfileControllerImpl;
import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.InvalidUsernameException;
import com.metagain.frontend.model.OwnProfile;

public class Register extends AppCompatActivity {

    private Button buttonRegister;

    private EditText etFirstName, etLastName, etUsername, etEmail, etPassword;

    private ProfileController profileController = new ProfileControllerImpl();

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = findViewById(R.id.editTextFirstName);
        etLastName = findViewById(R.id.editTextLastName);
        etUsername = findViewById(R.id.editTextUsernameRegistration);
        etPassword = findViewById(R.id.editTextPasswordRegistration);
        etEmail = findViewById(R.id.editTextEmail);

        buttonRegister = findViewById(R.id.buttonRegistertoHome);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                OwnProfile ownProfile = new OwnProfile(firstName, lastName, username, email, password);
                try {
                    Register.this.profileController.createAccount(ownProfile);
                    openHomepage();
                } catch (InvalidEmailException e) {
                    Toast.makeText(Register.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                } catch (InvalidUsernameException e) {
                    Toast.makeText(Register.this, "Username already exists", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void openHomepage() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }
}