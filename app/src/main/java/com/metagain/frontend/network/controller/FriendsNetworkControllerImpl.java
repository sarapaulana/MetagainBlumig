package com.metagain.frontend.network.controller;

import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.Friends;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.network.FriendsNetworkController;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.services.FriendsNetworkService;
import com.metagain.frontend.network.services.RequestNetworkService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;

public class FriendsNetworkControllerImpl implements FriendsNetworkController, Runnable {

    private int connected = 0;

    private Call<?> call;

    private Response<?> response;

    private FriendsNetworkService friendsNetworkService = NetworkConstants.createFriendsNetworkService();

    @Override
    public List<Friends> get() throws NetworkErrorException {
        call = friendsNetworkService.get(NetworkConstants.AUTHORIZATION);

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

        return (List<Friends>) response.body();
    }

    @Override
    public void delete(UUID friendsID) throws NetworkErrorException {
        call = friendsNetworkService.delete(NetworkConstants.AUTHORIZATION, friendsID);

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

    @Override
    public void run() {
        try {
            response = call.execute();
            call = null;
            System.out.println(response.code());
        } catch (IOException e) {
            connected = -1;
        } catch (Throwable throwable) {
            System.out.println(throwable.getLocalizedMessage());
        }
        synchronized (this) {
            this.notify();
        }
    }
}
