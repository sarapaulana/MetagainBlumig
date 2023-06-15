package com.metagain.frontend.network.controller;

import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.Friends;

import java.util.List;
import java.util.UUID;

public interface FriendsNetworkController {

    public List<Friends> get() throws NetworkErrorException;

    public void delete(UUID friendsID) throws NetworkErrorException;

}
