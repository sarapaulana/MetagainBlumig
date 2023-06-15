package com.metagain.frontend.controll.implementations;

import com.metagain.frontend.controll.RequestController;
import com.metagain.frontend.exceptions.InvalidRadiusException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.NotFriendsException;
import com.metagain.frontend.model.Profile;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.model.types.RequestType;
import com.metagain.frontend.network.controller.RequestNetworkController;
import com.metagain.frontend.network.controller.implementations.RequestNetworkControllerImpl;

import java.util.List;
import java.util.UUID;

public class RequestControllerImpl implements RequestController {

    private RequestNetworkController requestNetworkController = new RequestNetworkControllerImpl();

    @Override
    public void sendFollowRequest(String username) throws NetworkErrorException {
        Profile toFollow = new Profile(username);
        Request request = new Request(toFollow, RequestType.FOLLOW);
        try {
            requestNetworkController.post(request);
        } catch (NotFriendsException e) {
            //never happens
        }
    }

    @Override
    public void sendMeetingRequest(Profile friendsProfile) throws NetworkErrorException, NotFriendsException {
        Request request = new Request(friendsProfile, RequestType.MEET);
        requestNetworkController.post(request);
    }

    @Override
    public void sendRadiusRequest(Profile friendsProfile, int radius) throws NetworkErrorException, NotFriendsException, InvalidRadiusException {
        if (radius <= 0) {
            throw new InvalidRadiusException();
        }
        Request request = new Request(friendsProfile, RequestType.RADIUS, radius);
        requestNetworkController.post(request);
    }

    @Override
    public void acceptRequest(UUID requestID) throws NetworkErrorException {
        requestNetworkController.patch(requestID);
    }

    @Override
    public void deleteRequest(UUID requestID) throws NetworkErrorException {
        requestNetworkController.delete(requestID);
    }

    @Override
    public List<Request> getRequests() throws NetworkErrorException {
        List<Request> requestList = requestNetworkController.get();
        return requestList;
    }


    @Override
    public void insertNetworkController(RequestNetworkController requestNetworkController) {
        this.requestNetworkController = requestNetworkController;
    }
}
