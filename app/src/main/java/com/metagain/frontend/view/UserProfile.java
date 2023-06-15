package com.metagain.frontend.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.metagain.frontend.R;
import com.metagain.frontend.controll.ProfileController;
import com.metagain.frontend.controll.implementations.ProfileControllerImpl;
import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.InvalidUsernameException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.UsernameAlreadyExistsException;
import com.metagain.frontend.exceptions.handler.ActivityExceptionHandler;
import com.metagain.frontend.model.storage.ProfileDataStorage;
import com.metagain.frontend.services.LocationService;

public class UserProfile extends AppCompatActivity {


    boolean edited;
    ImageButton profileBack;
    Button abmelden;
    Button kontoLoeschen;

    ImageButton editUsername;

    ImageButton editPassword;

    ImageButton editEmail;

    Switch incognitoSwitch;

    ActivityExceptionHandler activityExceptionHandler = new ActivityExceptionHandler(this);

    int imageEdit = android.R.drawable.ic_menu_edit;

    int imageSave = android.R.drawable.ic_menu_save;

    ProfileController profileController = new ProfileControllerImpl();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        View usernameView = getLayoutInflater().inflate(R.layout.text_view_username, null);
        View emailView = getLayoutInflater().inflate(R.layout.text_view_email, null);
        View passwordView = getLayoutInflater().inflate(R.layout.text_view_password,  null);
        View editUsernameView = getLayoutInflater().inflate(R.layout.edit_text_username, null);
        View editEmailView = getLayoutInflater().inflate(R.layout.edit_text_email, null);
        View editPasswordView = getLayoutInflater().inflate(R.layout.edit_text_password, null);

        LinearLayout linearLayoutUsername = findViewById(R.id.linearLayoutUsernameCard);
        TextView usernameText = usernameView.findViewById(R.id.textRealUsername);
        usernameText.setText(ProfileDataStorage.getUsername());
        EditText editTextRealUsername = editUsernameView.findViewById(R.id.editTextRealUsername);

        LinearLayout linearLayoutEmail = findViewById(R.id.linearLayoutEmailCard);
        TextView emailText = emailView.findViewById(R.id.textRealEmail);
        emailText.setText(ProfileDataStorage.getEmail());
        EditText editTextRealEmail = editEmailView.findViewById(R.id.editTextRealEmail);

        LinearLayout linearLayoutPassword = findViewById(R.id.linearLayoutPasswordCard);
        TextView passwordText = passwordView.findViewById(R.id.textRealPassword);
        passwordText.setText(ProfileDataStorage.getPassword());
        EditText editTextRealPassword = editPasswordView.findViewById(R.id.editTextRealPassword);

        linearLayoutUsername.addView(usernameView);
        linearLayoutEmail.addView(emailView);
        linearLayoutPassword.addView(passwordView);

        incognitoSwitch = findViewById(R.id.switchIncognito);
        incognitoSwitch.setChecked(ProfileDataStorage.isIncognito());
        incognitoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    profileController.setIncognito(isChecked);
                } catch (NetworkErrorException e) {
                    activityExceptionHandler.handleNetworkErrorException();
                }
                Intent serviceIntent = new Intent(UserProfile.this, LocationService.class);
                if (isChecked) {
                    stopService(serviceIntent);
                } else {
                    ActivityCompat.requestPermissions(UserProfile.this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    }, 123);
                    startService(serviceIntent);
                }
            }
        });

        editUsername = findViewById(R.id.editBenutzername);
        editUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edited) {
                    linearLayoutUsername.removeView(editUsernameView);
                    linearLayoutUsername.addView(usernameView);
                    editUsername.setImageResource(imageEdit);

                    try {
                        profileController.editUsername(editTextRealUsername.getText().toString());
                    }  catch (InvalidUsernameException e) {
                        activityExceptionHandler.handleInvalidUsernameException();
                    } catch (NetworkErrorException e) {
                        activityExceptionHandler.handleNetworkErrorException();
                    } catch (UsernameAlreadyExistsException e) {
                        activityExceptionHandler.handleUsernameAlreadyExistsException();
                    }

                    usernameText.setText(ProfileDataStorage.getUsername());
                    edited = false;
                } else {

                    linearLayoutUsername.removeView(usernameView);
                    linearLayoutUsername.addView(editUsernameView);
                    editUsername.setImageResource(imageSave);

                    editTextRealUsername.setText(ProfileDataStorage.getUsername());
                    edited = true;
                }
            }
        });

        editEmail = findViewById(R.id.editEmail);
        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edited) {
                    linearLayoutEmail.removeView(editEmailView);
                    linearLayoutEmail.addView(emailView);
                    editEmail.setImageResource(imageEdit);

                    try {
                        profileController.editEmail(editTextRealEmail.getText().toString());
                    } catch (InvalidEmailException e) {
                        activityExceptionHandler.handleInvalidEmailExcpeption();
                    } catch (NetworkErrorException e) {
                        activityExceptionHandler.handleNetworkErrorException();
                    }

                    emailText.setText(ProfileDataStorage.getEmail());
                    edited = false;
                } else {
                    linearLayoutEmail.removeView(emailView);
                    linearLayoutEmail.addView(editEmailView);
                    editEmail.setImageResource(imageSave);

                    editTextRealEmail.setText(ProfileDataStorage.getEmail());
                    edited = true;
                }
            }
        });

        editPassword = findViewById(R.id.editPasswort);
        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edited) {
                    linearLayoutPassword.removeView(editPasswordView);
                    linearLayoutPassword.addView(passwordView);
                    editPassword.setImageResource(imageEdit);

                    try {
                        profileController.editPassword(editTextRealPassword.getText().toString());
                    } catch (NetworkErrorException e) {
                        activityExceptionHandler.handleNetworkErrorException();
                    }

                    passwordText.setText(ProfileDataStorage.getPassword());
                    edited = false;
                } else {
                    linearLayoutPassword.removeView(passwordView);
                    linearLayoutPassword.addView(editPasswordView);
                    editPassword.setImageResource(imageSave);

                    editTextRealPassword.setText(ProfileDataStorage.getPassword());
                    edited = true;
                }
            }
        });

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
                Intent serviceIntent = new Intent(UserProfile.this, LocationService.class);
                stopService(serviceIntent);
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
                    activityExceptionHandler.handleNetworkErrorException();
                }
                Intent serviceIntent = new Intent(UserProfile.this, LocationService.class);
                stopService(serviceIntent);
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