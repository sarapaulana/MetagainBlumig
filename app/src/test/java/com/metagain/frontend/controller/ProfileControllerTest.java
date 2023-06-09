package com.metagain.frontend.controller;

import com.metagain.frontend.controll.ProfileController;
import com.metagain.frontend.controll.implementations.ProfileControllerImpl;
import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.network.controller.ProfileNetworkControllerImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProfileControllerTest {

    ProfileController profileController = new ProfileControllerImpl();

    ProfileNetworkControllerImpl profileNetworkControllerMock = Mockito.mock(ProfileNetworkControllerImpl.class);

    @Test
    public void emailGoodCreateProfileTest() throws NetworkErrorException, InvalidEmailException {
        profileController.insertNetworkController(profileNetworkControllerMock);
        OwnProfile ownProfile = new OwnProfile("Grischa", "Storch", "grr", "grischa.storch@gmail.com", "1234");
        profileController.createAccount(ownProfile);

        Mockito.verify(profileNetworkControllerMock).post(ownProfile);
    }
}
