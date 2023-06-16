package com.metagain.frontend.controller;

import com.metagain.frontend.controll.FriendsController;
import com.metagain.frontend.controll.implementations.FriendsControllerImpl;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.Friends;
import com.metagain.frontend.model.Profile;
import com.metagain.frontend.network.controller.implementations.FriendsNetworkControllerImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

public class FriendsControllerTest {

    FriendsNetworkControllerImpl friendsNetworkControllerMock = Mockito.mock(FriendsNetworkControllerImpl.class);

    FriendsController friendsController = new FriendsControllerImpl();

    @BeforeEach
    void init(){
        friendsController.insertNetworkController(friendsNetworkControllerMock);
    }

//////////////////////////////////////////////////get Friends///////////////////////////////////////////////////////////////////////////////

    @Test
    public void getFriendsGoodFriendsControllerTest() throws NetworkErrorException {

        Profile p1 = new Profile("gr");
        Profile p2 = new Profile("gre");


        Friends f1 = new Friends(UUID.randomUUID(), p1,1000, false);

        Friends f2 = new Friends(UUID.randomUUID(), p2,1000, false);

        List<Friends> friendlist = List.of(f1,f2);

        Mockito.when(friendsNetworkControllerMock.get()).thenReturn(friendlist);

        List<Friends> realFriends = friendsController.getFriends();

        Mockito.verify(friendsNetworkControllerMock).get();
    }

/////////////////////////////////////////////////get Friends in Radius//////////////////////////////////////////////////////////////////////

    @Test
    public void getFriendsInRadiusGoodFriendsControllerTest() throws NetworkErrorException {

        Profile p1 = new Profile("gr");
        Profile p2 = new Profile("gre");


        Friends f1 = new Friends(UUID.randomUUID(), p1,1000, false);

        Friends f2 = new Friends(UUID.randomUUID(), p2,900, true);

        List<Friends> friendlist = List.of(f1,f2);

        Mockito.when(friendsNetworkControllerMock.get()).thenReturn(friendlist);

        List<Friends> realFriendsInRadius = friendsController.getFriendsInRadius();

        Mockito.verify(friendsNetworkControllerMock).get();
    }

////////////////////////////////////////////////delete Friend//////////////////////////////////////////////////////////////////////////////

    @Test
    public void deleteFriendGoodFriendControllerTest() throws NetworkErrorException {

        Profile p1 = new Profile("gr");
        Profile p2 = new Profile("gre");


        Friends f1 = new Friends(UUID.randomUUID(), p1,1000, false);

        Friends f2 = new Friends(UUID.randomUUID(), p2,900, true);

        friendsController.deleteFriend(f1.getId());

        Mockito.verify(friendsNetworkControllerMock).delete(f1.getId());


    }

}
