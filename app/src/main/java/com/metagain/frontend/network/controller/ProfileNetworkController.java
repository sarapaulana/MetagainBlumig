package com.metagain.frontend.network.controller;

import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.LoginException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.services.ProfileNetworkService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ProfileNetworkController implements Runnable {

    private int connected = 0;

    private Call<?> call;

    private Response<?> response;


    private ProfileNetworkService profileNetworkService = NetworkConstants.getProfileNetworkController();

    public void post(OwnProfile ownProfile) throws InvalidEmailException, NetworkErrorException{
        call = profileNetworkService.post(ownProfile);

        Thread execute = new Thread(this);
        execute.start();

        synchronized(this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (connected == -1) {
            connected = 0;
            throw new NetworkErrorException();
        }

    }

    public void get() throws NetworkErrorException, LoginException {
        call = profileNetworkService.get(NetworkConstants.AUTHORIZATION);

        Thread execute = new Thread(this);
        execute.start();

        synchronized(this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (connected == -1) {
            connected = 0;
            throw new NetworkErrorException();
        } else if(response.code() == 401) {
            throw new LoginException();
        } else {
            OwnProfile ownProfile = (OwnProfile) response.body();
        }
    }


    @Override
    public void run() {
        try {
            response = call.execute();
        } catch (IOException e) {
            connected = -1;
        }
        synchronized (this) {
            this.notify();
        }
    }
}
