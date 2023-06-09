package com.metagain.frontend.controll.implementations;

import com.metagain.frontend.controll.ProfileController;
import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.InvalidUsernameException;
import com.metagain.frontend.exceptions.LoginException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.model.storage.ProfileDataStorage;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.controller.ProfileNetworkControllerImpl;
import com.metagain.frontend.validator.EmailValidator;


public class ProfileControllerImpl implements ProfileController {

    private final ProfileNetworkControllerImpl profileNetworkController = new ProfileNetworkControllerImpl();


    @Override
    public void createAccount(OwnProfile ownProfile) throws InvalidEmailException, NetworkErrorException {
        if (!EmailValidator.isValidEmail(ownProfile.getEmail())) {
            throw new InvalidEmailException();
        }
        profileNetworkController.post(ownProfile);
        NetworkConstants.setAuthorization(ownProfile.getUsername(), ownProfile.getPassword());
    }

    @Override
    public void editProfile(OwnProfile ownProfile) throws InvalidUsernameException, InvalidEmailException {

    }

    @Override
    public OwnProfile getOwnProfile() {
        return null;
    }

    @Override
    public void login(String username, String password) throws LoginException, NetworkErrorException {
        NetworkConstants.setAuthorization(username, password);
        OwnProfile ownProfile = profileNetworkController.get();
        ProfileDataStorage.setUsername(ownProfile.getUsername());
        ProfileDataStorage.setEmail(ownProfile.getEmail());
        ProfileDataStorage.setPassword(password);

    }

    @Override
    public void logout() {
        NetworkConstants.removeAuthorization();
        ProfileDataStorage.setUsername("");
        ProfileDataStorage.setEmail("");
        ProfileDataStorage.setPassword("");
    }

    @Override
    public void delete() throws NetworkErrorException {
        profileNetworkController.delete();

        NetworkConstants.removeAuthorization();
        ProfileDataStorage.setUsername("");
        ProfileDataStorage.setEmail("");
        ProfileDataStorage.setPassword("");
    }

    @Override
    public void insertNetworkController(ProfileNetworkControllerImpl profileNetworkController) {

    }


}
