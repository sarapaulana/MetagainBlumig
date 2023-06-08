package com.metagain.frontend.controll.implementations;

import com.metagain.frontend.controll.FriendsController;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.Friends;
import com.metagain.frontend.network.FriendsNetworkController;
import com.metagain.frontend.network.controller.FriendsNetworkControllerImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FriendsControllerImpl implements FriendsController {

    private FriendsNetworkController friendsNetworkController = new FriendsNetworkControllerImpl();

    @Override
    public List<Friends> getFriends() throws NetworkErrorException {
        List<Friends> friendsList = friendsNetworkController.get();
        return friendsList;
    }

    @Override
    public List<Friends> getFriendsInRadius() throws NetworkErrorException {
        List<Friends> friendsInRadiusList = getFriends().stream().filter((f) -> f.isInRadius()).collect(Collectors.toList());
        return friendsInRadiusList;
    }

    @Override
    public void deleteFriend(UUID friendsID) throws NetworkErrorException {
        friendsNetworkController.delete(friendsID);
    }

    @Override
    public boolean follows(UUID profileID) {
        return false;
    }

    @Override
    public void insertNetworkController(FriendsNetworkController friendsNetworkController) {

    }
}
