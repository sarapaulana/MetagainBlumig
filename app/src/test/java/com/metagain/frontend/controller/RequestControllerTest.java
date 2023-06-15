package com.metagain.frontend.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.metagain.frontend.controll.RequestController;
import com.metagain.frontend.controll.implementations.RequestControllerImpl;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.NotFriendsException;
import com.metagain.frontend.model.Profile;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.model.types.RequestType;
import com.metagain.frontend.network.controller.implementations.RequestNetworkControllerImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class RequestControllerTest {

    RequestNetworkControllerImpl requestNetworkControllerMock = Mockito.mock(RequestNetworkControllerImpl.class);

    RequestController requestController = new RequestControllerImpl();

    @BeforeEach
    void inti(){
        requestController.insertNetworkController(requestNetworkControllerMock);

    }

///////////////////////////////////////////////////send Request///////////////////////////////////////////////////////////////////////////////

    @Test
    public void sendRequestGoodRequestControllerTest() throws NetworkErrorException, NotFriendsException {
        Profile p1 = new Profile("grr");

        Request r1 = new Request(p1, RequestType.MEET);

        requestController.sendMeetingRequest(p1);
        Mockito.verify(requestNetworkControllerMock).post(r1); //TODO
    }

    @Test
    public void sendRequestNotFriendsExceptionTest() throws NetworkErrorException, NotFriendsException {
        Profile p1 = new Profile("grr");

        Request r1 = new Request(p1, RequestType.MEET);

        //TODO notFriendsException auslösen

        assertThrows(NotFriendsException.class, () ->{
            requestController.sendMeetingRequest(p1);
        });
    }

//////////////////////////////////////////////////accept Request/////////////////////////////////////////////////////////////////////////////

    @Test
    public void acceptRequestGoodRequestControllerTest() throws NetworkErrorException {
        Profile p1 = new Profile("grr");

        Request r1 = new Request(p1, RequestType.MEET);
        requestController.acceptRequest(r1.getId());
        Mockito.verify(requestNetworkControllerMock).patch(r1.getId());
    }

/////////////////////////////////////////////////delete Request/////////////////////////////////////////////////////////////////////////////

    @Test
    public void deleteRequestGoodRequestControllerTest() throws NetworkErrorException {
        Profile p1 = new Profile("grr");

        Request r1 = new Request(p1, RequestType.MEET);

        requestController.deleteRequest(r1.getId());
        Mockito.verify(requestNetworkControllerMock).delete(r1.getId());
    }

/////////////////////////////////////////////////get Request////////////////////////////////////////////////////////////////////////////////

    @Test
    public void getRequestGoodRequestControllerTest() throws NetworkErrorException {
        Profile p1 = new Profile("grr");

        Request r1 = new Request(p1, RequestType.MEET);

        List<Request> requestList = List.of(r1);

        List<Request> realRequestList = new ArrayList<>();

        Mockito.when(requestNetworkControllerMock.get()).thenReturn(requestList);
        realRequestList = requestController.getRequests();

        Mockito.verify(requestNetworkControllerMock).get();
    }
}
