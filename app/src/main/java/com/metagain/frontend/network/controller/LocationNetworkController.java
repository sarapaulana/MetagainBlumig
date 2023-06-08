package com.metagain.frontend.network.controller;

import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.services.LocationNetworkService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LocationNetworkController implements Runnable {

    LocationNetworkService locationNetworkService = NetworkConstants.createLocationNetworkService();

    private int connected = 0;

    private Call<?> call;

    private Response<?> response;

    public void put(double[] location) throws NetworkErrorException {
        call = locationNetworkService.put(NetworkConstants.AUTHORIZATION, location);

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
            synchronized (this) {
                response = call.execute();
                call = null;
                System.out.println(response.code());
            }
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
