package com.metagain.frontend.network.controller;

import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.NotFriendsException;
import com.metagain.frontend.model.Request;

import java.util.List;
import java.util.UUID;

public interface RequestNetworkController {

    public void post(Request request) throws NotFriendsException, NetworkErrorException;

    public List<Request> get() throws NetworkErrorException;

    public void delete(UUID requestID) throws NetworkErrorException;

    public void patch(UUID requestID) throws NetworkErrorException;

}
