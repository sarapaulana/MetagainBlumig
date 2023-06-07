package com.metagain.frontend.controll.implementations;

import com.metagain.frontend.controll.ProfileController;
import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.InvalidUsernameException;
import com.metagain.frontend.exceptions.LoginException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.controller.ProfileNetworkController;
import com.metagain.frontend.network.services.ProfileNetworkService;
import com.metagain.frontend.validator.EmailValidator;


public class ProfileControllerImpl implements ProfileController {

    private final ProfileNetworkController profileNetworkController = new ProfileNetworkController();

    private int code;

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
        profileNetworkController.get();

    }

    @Override
    public void insertNetworkController(ProfileNetworkController profileNetworkController) {

    }


}
