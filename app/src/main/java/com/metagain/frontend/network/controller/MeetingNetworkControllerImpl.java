package com.metagain.frontend.network.controller;

import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.Meeting;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.network.MeetingNetworkController;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.services.MeetingNetworkService;
import com.metagain.frontend.network.services.RequestNetworkService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;

public class MeetingNetworkControllerImpl implements MeetingNetworkController, Runnable {

    private int connected = 0;

    private Call<?> call;

    private Response<?> response;

    private MeetingNetworkService meetingNetworkService = NetworkConstants.createMeetingNetworkService();

    @Override
    public List<Meeting> get() throws NetworkErrorException {
        call = meetingNetworkService.get(NetworkConstants.AUTHORIZATION);

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
        System.out.println(response.body());
        return (List<Meeting>) response.body();
    }

    @Override
    public void delete(UUID meetingID) throws NetworkErrorException {
        call = meetingNetworkService.delete(NetworkConstants.AUTHORIZATION, meetingID);

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
    public void put(Meeting meeting) throws NetworkErrorException {
        call = meetingNetworkService.put(NetworkConstants.AUTHORIZATION, meeting);

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
