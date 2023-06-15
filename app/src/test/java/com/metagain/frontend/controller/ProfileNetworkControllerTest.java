package com.metagain.frontend.controller;

import com.metagain.frontend.controll.ProfileController;
import com.metagain.frontend.controll.implementations.ProfileControllerImpl;
import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.InvalidUsernameException;
import com.metagain.frontend.exceptions.LoginException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.UsernameAlreadyExistsException;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.network.controller.implementations.ProfileNetworkControllerImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProfileNetworkControllerTest {

    ProfileController profileController = new ProfileControllerImpl();

    ProfileNetworkControllerImpl profileNetworkControllerMock = Mockito.mock(ProfileNetworkControllerImpl.class);



//////////////////////////////////////////Create Profile////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void emailGoodCreateProfileTest() throws NetworkErrorException, InvalidUsernameException, UsernameAlreadyExistsException, InvalidEmailException {
        profileController.insertNetworkController(profileNetworkControllerMock);
        OwnProfile ownProfile = new OwnProfile("Grischa", "Storch", "grr", "grischa.storch@gmail.com", "1234");
        profileController.createAccount(ownProfile);

        Mockito.verify(profileNetworkControllerMock).post(ownProfile);
    }

    @Test
    public void emailInvalidCreateProfileTest() throws NetworkErrorException, InvalidEmailException {
        profileController.insertNetworkController(profileNetworkControllerMock);
        OwnProfile ownProfile = new OwnProfile("Grischa", "Storch", "grr", "grischa.storchgmail.com", "1234");

        assertThrows(InvalidEmailException.class, () -> {
            profileController.createAccount(ownProfile);
        });

    }

////////////////////////////////////////Edit Email ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void editEmailGoodProfileTest() throws NetworkErrorException, InvalidEmailException, InvalidUsernameException, UsernameAlreadyExistsException {

        profileController.insertNetworkController(profileNetworkControllerMock);
        OwnProfile ownProfile = new OwnProfile("Grischa", "Storch", "grr", "grischa.storch@gmail.com", "1234");
        profileController.createAccount(ownProfile);
        profileController.editEmail("grischa.st@gmail.com");

        Mockito.verify(profileNetworkControllerMock).put(ownProfile);
    }

    @Test
    public void editEmailInvalidProfileTest() throws NetworkErrorException, InvalidEmailException, InvalidUsernameException, UsernameAlreadyExistsException {
        profileController.insertNetworkController(profileNetworkControllerMock);
        OwnProfile ownProfile = new OwnProfile("Grischa", "Storch", "grr", "grischa.storch@gmail.com", "1234");
        profileController.createAccount(ownProfile);

        assertThrows(InvalidEmailException.class, () -> {
            profileController.editEmail("grischa.st.gmail.com");
        });

    }

//////////////////////////Edit Username///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void editUsernameGoodProfileTest() throws InvalidUsernameException, NetworkErrorException, InvalidEmailException, UsernameAlreadyExistsException {
        profileController.insertNetworkController(profileNetworkControllerMock);
        OwnProfile ownProfile = new OwnProfile("Grischa", "Storch", "grr", "grischa.storch@gmail.com", "1234");
        profileController.createAccount(ownProfile);
        profileController.editUsername("grrischa");

        Mockito.verify(profileNetworkControllerMock).put(ownProfile);
    }

    @Test
    public void editUsernameInvalidProfileTest() throws InvalidUsernameException, NetworkErrorException, InvalidEmailException, UsernameAlreadyExistsException {
        profileController.insertNetworkController(profileNetworkControllerMock);
        OwnProfile ownProfile = new OwnProfile("Grischa", "Storch", "grr", "grischa.storch@gmail.com", "1234");
        profileController.createAccount(ownProfile);
        OwnProfile ownProfile2 = new OwnProfile("Grischa", "Storch", "grrischa", "grischa.storch@gmail.com", "1234");
        profileController.createAccount(ownProfile2);


        assertThrows(InvalidUsernameException.class, () ->{
            profileController.editUsername("grrischa");
        });
    }

/////////////////////////Edit Password////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void editPasswordGoodProfileTest() throws InvalidEmailException, InvalidUsernameException, NetworkErrorException, UsernameAlreadyExistsException {
        profileController.insertNetworkController(profileNetworkControllerMock);
        OwnProfile ownProfile = new OwnProfile("Grischa", "Storch", "grr", "grischa.storch@gmail.com", "1234");
        profileController.createAccount(ownProfile);
        profileController.editPassword("12345");

        Mockito.verify(profileNetworkControllerMock).put(ownProfile);
    }


////////////////////////Login/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void loginGoodProfileTest() throws LoginException, NetworkErrorException, InvalidEmailException {
        profileController.insertNetworkController(profileNetworkControllerMock);
        OwnProfile ownProfile = new OwnProfile("Grischa", "Storch", "grr", "grischa.storch@gmail.com", "1234");

        Mockito.when(profileNetworkControllerMock.get()).thenReturn(ownProfile);
        profileController.login("grr","1234");

        Mockito.verify(profileNetworkControllerMock).get();
    }

    @Test
    public void loginExceptionProfileTest() throws LoginException, NetworkErrorException {
        profileController.insertNetworkController(profileNetworkControllerMock);
        OwnProfile ownProfile = new OwnProfile("Grischa", "Storch", "grr", "grischa.storch@gmail.com", "1234");

        Mockito.when(profileNetworkControllerMock.get()).thenThrow(LoginException.class);

        assertThrows(LoginException.class, () ->{
            profileController.login("grr","12");
        });
    }

///////////////////////Logout/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////Delete//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void deleteGoodProfileTest() throws NetworkErrorException, InvalidEmailException, UsernameAlreadyExistsException, InvalidUsernameException {
        profileController.insertNetworkController(profileNetworkControllerMock);
        OwnProfile ownProfile = new OwnProfile("Grischa", "Storch", "grr", "grischa.storch@gmail.com", "1234");
        profileController.createAccount(ownProfile);
        profileController.delete();

        Mockito.verify(profileNetworkControllerMock).delete();
    }

}
