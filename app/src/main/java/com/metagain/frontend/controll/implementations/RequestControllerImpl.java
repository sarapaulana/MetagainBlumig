package com.metagain.frontend.controll.implementations;

import com.metagain.frontend.controll.RequestController;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.NotFriendsException;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.network.RequestNetworkController;
import com.metagain.frontend.network.controller.RequestNetworkControllerImpl;

import java.util.List;
import java.util.UUID;

public class RequestControllerImpl implements RequestController {

    private RequestNetworkController requestNetworkController = new RequestNetworkControllerImpl();
    @Override
    public void sendRequest(Request request) throws NotFriendsException, NetworkErrorException {
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
