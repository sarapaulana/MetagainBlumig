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


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MeetingControllerTest {

    MeetingController meetingController = new MeetingControllerImpl();


    MeetingNetworkControllerImpl meetingNetworkControllerMock = Mockito.mock(MeetingNetworkControllerImpl.class);

    @BeforeEach
    void init(){
        meetingController.insertNetworkController(meetingNetworkControllerMock);
    }

///////////////////////////////update Meeting Point///////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void updateMeetingPointGoodMeetingControllerTest() throws CoordinatesFormatException, NetworkErrorException, InvalidEmailException, NotFriendsException {



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
