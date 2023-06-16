package com.metagain.frontend.network;

import com.metagain.frontend.exceptions.LoginException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.UsernameAlreadyExistsException;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.network.controller.ProfileNetworkController;
import com.metagain.frontend.network.controller.implementations.ProfileNetworkControllerImpl;
import com.metagain.frontend.network.services.ProfileNetworkService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ProfileNetworkControllerTests {

    ProfileNetworkController profileNetworkController = new ProfileNetworkControllerImpl();

    ProfileNetworkService profileNetworkServiceMock = Mockito.mock(ProfileNetworkService.class);

    Call<OwnProfile> getCallMock = Mockito.mock(Call.class);

    Call<Void> voidCallMock = Mockito.mock((Call.class));

    Response<OwnProfile> getResponseMock = Mockito.mock(Response.class);

    Response<Void> voidResponseMock = Mockito.mock(Response.class);

    OwnProfile exampleProfile = new OwnProfile("Alice", "Ecila", "alice", "alice@gmail.com", "password");

    @BeforeEach
    public void init() throws IOException {
        profileNetworkController.insertNetworkService(profileNetworkServiceMock);
        Mockito.when(profileNetworkServiceMock.get(ArgumentMatchers.anyString())).thenReturn(getCallMock);
        Mockito.when(profileNetworkServiceMock.put(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(voidCallMock);
        Mockito.when(profileNetworkServiceMock.post(ArgumentMatchers.any())).thenReturn(voidCallMock);
        Mockito.when(profileNetworkServiceMock.delete(ArgumentMatchers.anyString())).thenReturn(voidCallMock);
        Mockito.when(getCallMock.execute()).thenReturn(getResponseMock);
        Mockito.when(voidCallMock.execute()).thenReturn(voidResponseMock);
    }

//////////////////////////////////////////Get Own Profile////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void goodGetProfileTest() throws NetworkErrorException, LoginException {
        Mockito.when(getResponseMock.code()).thenReturn(200);
        Mockito.when(getResponseMock.body()).thenReturn(exampleProfile);


        Assertions.assertEquals(exampleProfile, profileNetworkController.get());
    }

    @Test
    public void badGetNetworkErrorTest() throws IOException {
        Mockito.when(getCallMock.execute()).thenReturn(null);

        Assertions.assertThrows(NetworkErrorException.class, () -> profileNetworkController.get());
    }

    @Test
    public void badGetLoginException() {
        Mockito.when(getResponseMock.code()).thenReturn(401);
        Assertions.assertThrows(LoginException.class, () -> profileNetworkController.get());
    }

//////////////////////////////////////////Put edited Profile////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void goodPutProfileTest() throws NetworkErrorException, UsernameAlreadyExistsException {
        Mockito.when(voidResponseMock.code()).thenReturn(200);
        profileNetworkController.put(exampleProfile);
        // should pass
    }

    @Test
    public void badPutUsernameAlreadyExistsTest() {
        Mockito.when(voidResponseMock.code()).thenReturn(409);

        Assertions.assertThrows(UsernameAlreadyExistsException.class, () -> profileNetworkController.put(exampleProfile));
    }

    @Test
    public void badPutNetworkErrorTest() throws IOException {
        Mockito.when(voidCallMock.execute()).thenReturn(null);

        Assertions.assertThrows(NetworkErrorException.class, () -> profileNetworkController.put(exampleProfile));
    }

//////////////////////////////////////////Post Profile////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void goodPostProfileTest() throws NetworkErrorException, UsernameAlreadyExistsException {
        Mockito.when(voidResponseMock.code()).thenReturn(200);
        profileNetworkController.post(exampleProfile);
        // should pass
    }

    @Test
    public void badPostUsernameAlreadyExistsTest() {
        Mockito.when(voidResponseMock.code()).thenReturn(409);

        Assertions.assertThrows(UsernameAlreadyExistsException.class, () -> profileNetworkController.post(exampleProfile));
    }

    @Test
    public void badPostNetworkErrorTest() throws IOException {
        Mockito.when(voidCallMock.execute()).thenReturn(null);

        Assertions.assertThrows(NetworkErrorException.class, () -> profileNetworkController.post(exampleProfile));
    }

//////////////////////////////////////////Post Profile////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void goodDeleteProfileTest() throws NetworkErrorException {
        Mockito.when(voidResponseMock.code()).thenReturn(200);
        profileNetworkController.delete();
        //should pass
    }

    @Test
    public void badDeleteNetworkError() throws IOException {
        Mockito.when(voidCallMock.execute()).thenReturn(null);

        Assertions.assertThrows(NetworkErrorException.class, () -> profileNetworkController.delete());
    }
}
