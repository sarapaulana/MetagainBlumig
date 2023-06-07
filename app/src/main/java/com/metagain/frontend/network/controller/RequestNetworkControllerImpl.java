package com.metagain.frontend.network.controller;

import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.NotFriendsException;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.RequestNetworkController;
import com.metagain.frontend.network.services.RequestNetworkService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;

public class RequestNetworkControllerImpl implements Runnable, RequestNetworkController {

    private int connected = 0;

    private Call<?> call;

    private Response<?> response;

    private RequestNetworkService requestNetworkService = NetworkConstants.createRequestNetworkService();

    public void post(Request request) throws NotFriendsException, NetworkErrorException {

        call = requestNetworkService.post(NetworkConstants.AUTHORIZATION, request);


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
        System.out.println("Jubbiii");
        //TODO
    }

    public List<Request> get() throws NetworkErrorException {
        call = requestNetworkService.get(NetworkConstants.AUTHORIZATION);

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

        return (List<Request>) response.body();
    }

    public void delete(UUID requestID) throws NetworkErrorException {

        call = requestNetworkService.delete(NetworkConstants.AUTHORIZATION, requestID);


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
    public void patch(UUID requestID) throws NetworkErrorException {
        call = requestNetworkService.patch(NetworkConstants.AUTHORIZATION, requestID);


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
