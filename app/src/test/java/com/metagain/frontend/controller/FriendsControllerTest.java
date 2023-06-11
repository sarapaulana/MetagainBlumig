package com.metagain.frontend.controller;

import com.metagain.frontend.controll.FriendsController;
import com.metagain.frontend.controll.implementations.FriendsControllerImpl;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.Friends;
import com.metagain.frontend.network.controller.FriendsNetworkControllerImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class FriendsControllerTest {

    FriendsNetworkControllerImpl friendsNetworkController = Mockito.mock(FriendsNetworkControllerImpl.class);

    FriendsController friendsController = new FriendsControllerImpl();

//////////////////////////////////////////////////get Friends///////////////////////////////////////////////////////////////////////////////

    @Test
    public void getFriendsGoodFriendsControllerTest() throws NetworkErrorException {
        List<Friends> f = new ArrayList<>();
        f = friendsController.getFriends();
        Mockito.verify(friendsNetworkController).get();
    }

/////////////////////////////////////////////////get Friends in Radius//////////////////////////////////////////////////////////////////////

    @Test
    public void getFriendsInRadiusGoodFriendsControllerTest(){

    }

////////////////////////////////////////////////delete Friend//////////////////////////////////////////////////////////////////////////////

    @Test
    public void deleteFriendGoodFriendControllerTest(){

    }

}
