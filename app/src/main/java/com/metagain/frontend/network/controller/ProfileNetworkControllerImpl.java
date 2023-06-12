package com.metagain.frontend.network.controller;

import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.InvalidUsernameException;
import com.metagain.frontend.exceptions.LoginException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.services.ProfileNetworkService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ProfileNetworkControllerImpl implements Runnable {

    private int connected = 0;

    private Call<?> call;

    private Response<?> response;


    private ProfileNetworkService profileNetworkService = NetworkConstants.createProfileNetworkService();

//    public ProfileNetworkControllerImpl(int connected, Call<?> call, Response<?> response, ProfileNetworkService profileNetworkService) {
//        this.connected = connected;
//        this.call = call;
//        this.response = response;
//        this.profileNetworkService = profileNetworkService;
//    }
//
//    public ProfileNetworkControllerImpl() {
//
//    }
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

    public OwnProfile get() throws NetworkErrorException, LoginException {
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

        OwnProfile ownProfile;

        if (connected == -1) {
            connected = 0;
            throw new NetworkErrorException();
        } else if(response.code() == 401) {
            throw new LoginException();
        } else {
            ownProfile = (OwnProfile) response.body();
        }
        return ownProfile;
    }

    public void delete() throws  NetworkErrorException {
        try {
            call = profileNetworkService.delete(NetworkConstants.AUTHORIZATION);
        } catch (Throwable t) {
            System.out.println(t.toString());
        }

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

    public void put(OwnProfile ownProfile) throws InvalidUsernameException, NetworkErrorException, InvalidEmailException {
        call = profileNetworkService.put(NetworkConstants.AUTHORIZATION, ownProfile);

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

        //TODO Exceptions (edit Username)
    }




    @Override
    public void run() {
        try {
            response = call.execute();
            System.out.println(response.code());
        } catch (IOException e) {
            connected = -1;
        } catch (Throwable t) {
            System.out.println(t);
        }
        synchronized (this) {
            this.notify();
        }
    }
}
