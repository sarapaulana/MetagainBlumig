package com.metagain.frontend.controller;

import com.metagain.frontend.controll.MeetingController;
import com.metagain.frontend.controll.ProfileController;
import com.metagain.frontend.controll.RequestController;
import com.metagain.frontend.controll.implementations.MeetingControllerImpl;
import com.metagain.frontend.controll.implementations.ProfileControllerImpl;
import com.metagain.frontend.controll.implementations.RequestControllerImpl;
import com.metagain.frontend.exceptions.CoordinatesFormatException;
import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.NotFriendsException;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.model.Profile;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.model.types.RequestType;
import com.metagain.frontend.network.controller.MeetingNetworkControllerImpl;
import com.metagain.frontend.network.controller.ProfileNetworkControllerImpl;
import com.metagain.frontend.network.controller.RequestNetworkControllerImpl;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MeetingControllerTest {

    MeetingController meetingController = new MeetingControllerImpl();

    ProfileController profileController = new ProfileControllerImpl();

    RequestController requestController = new RequestControllerImpl();

    ProfileNetworkControllerImpl profileNetworkController = new ProfileNetworkControllerImpl();

    RequestNetworkControllerImpl requestNetworkController = new RequestNetworkControllerImpl();

    MeetingNetworkControllerImpl meetingNetworkControllerMock = Mockito.mock(MeetingNetworkControllerImpl.class);

///////////////////////////////update Meeting Point///////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void updateMeetingPointGoodMeetingControllerTest() throws CoordinatesFormatException, NetworkErrorException, InvalidEmailException, NotFriendsException {

        profileController.insertNetworkController(profileNetworkController);
        OwnProfile ownProfile1 = new OwnProfile("Grischa", "Storch", "grr1", "grischa.storch@gmail.com", "1234");
        profileController.createAccount(ownProfile1);
        profileController.insertNetworkController(profileNetworkController);
        OwnProfile ownProfile2 = new OwnProfile("Grischa", "Storch", "grr2", "grischa.storch@gmail.com", "1234");
        profileController.createAccount(ownProfile2);

        Profile profile1 = new Profile("grr1");

        Profile profile2 = new Profile("grr2");

        Request request1 = new Request(profile1, RequestType.MEET);
        requestController.sendRequest(request1);
        requestController.acceptRequest(profile1.getId());


    }

//////////////////////////////get Meetings////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void getMeetingGoodMeetingControllerTest() throws NetworkErrorException {

    }

/////////////////////////////delete Meeting///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void deleteMeetingGoodMeetingControllerTest() throws NetworkErrorException{

    }
}
