package com.metagain.frontend.exceptions.handler;

import android.content.Context;
import android.widget.Toast;


public class ActivityExceptionHandler {

    private Context context;

    public ActivityExceptionHandler(Context context) {
        this.context = context;
    }

    public void handleNetworkErrorException() {
        Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
    }

    public void handleUsernameAlreadyExistsException() {
        Toast.makeText(context, "Username is already in use!", Toast.LENGTH_SHORT).show();
    }

    public void handleNotFriendsExcpetion() {
        Toast.makeText(context, "That's no friend!", Toast.LENGTH_SHORT).show();
    }

    public void handleLoginException() {
        Toast.makeText(context, "Wrong username or password", Toast.LENGTH_SHORT).show();
    }

    public void handleInvalidUsernameException() {
        Toast.makeText(context, "Invalid Username!", Toast.LENGTH_SHORT).show();
    }

    public void handleInvalidEmailExcpeption() {
        Toast.makeText(context, "Invalid Email!", Toast.LENGTH_SHORT).show();
    }

    public void handleCoordinatesFormatException() {
        Toast.makeText(context, "Why?", Toast.LENGTH_SHORT).show();
    }

    public void handleInvalidRadiusException() {
        Toast.makeText(context, "Invalid Radius", Toast.LENGTH_SHORT).show();
    }

}
