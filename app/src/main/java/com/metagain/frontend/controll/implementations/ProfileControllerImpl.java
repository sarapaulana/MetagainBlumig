package com.metagain.frontend.controll.implementations;

import com.metagain.frontend.controll.ProfileController;
import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.InvalidUsernameException;
import com.metagain.frontend.exceptions.LoginException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.UsernameAlreadyExistsException;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.model.storage.ProfileDataStorage;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.controller.implementations.ProfileNetworkControllerImpl;
import com.metagain.frontend.validator.EmailValidator;
import com.metagain.frontend.validator.UsernameValidator;


public class ProfileControllerImpl implements ProfileController {

    private ProfileNetworkControllerImpl profileNetworkController = new ProfileNetworkControllerImpl();


    @Override
    public void createAccount(OwnProfile ownProfile) throws InvalidUsernameException, NetworkErrorException, UsernameAlreadyExistsException, InvalidEmailException {
        if (!EmailValidator.isValidEmail(ownProfile.getEmail())) {
            throw new InvalidEmailException();
        } else if (!UsernameValidator.isValidUsername(ownProfile.getUsername())) {
            throw new InvalidUsernameException();
        }
        profileNetworkController.post(ownProfile);

        ProfileDataStorage.setOwnProfile(ownProfile);
        NetworkConstants.setAuthorization(ownProfile.getUsername(), ownProfile.getPassword());
    }

    @Override
    public void editProfile(OwnProfile ownProfile) throws InvalidUsernameException, InvalidEmailException {

    }

    @Override
    public void editUsername(String username) throws InvalidUsernameException, NetworkErrorException, UsernameAlreadyExistsException {
        if (!UsernameValidator.isValidUsername(username)) {
            throw new InvalidUsernameException();
        }
        OwnProfile ownProfile = ProfileDataStorage.getOwnProfile();
        ownProfile.setUsername(username);
        profileNetworkController.put(ownProfile);
        ProfileDataStorage.setUsername(username);
        NetworkConstants.setAuthorization(username, ProfileDataStorage.getPassword());
    }

    @Override
    public void editEmail(String email) throws InvalidEmailException, NetworkErrorException {
        if (!EmailValidator.isValidEmail(email)) {
            throw new InvalidEmailException();
        }
        OwnProfile ownProfile = ProfileDataStorage.getOwnProfile();
        ownProfile.setEmail(email);
        ProfileDataStorage.setEmail(email);
        try {
            profileNetworkController.put(ownProfile);
        } catch (UsernameAlreadyExistsException e) {
            //wont happen
        }
    }

    @Override
    public void editPassword(String password) throws  NetworkErrorException {
        OwnProfile ownProfile = ProfileDataStorage.getOwnProfile();
        ownProfile.setPassword(password);
        ProfileDataStorage.setPassword(password);
        try {
            profileNetworkController.put(ownProfile);
        } catch (UsernameAlreadyExistsException e) {
            //wont happen
        }
        NetworkConstants.setAuthorization(ProfileDataStorage.getUsername(), password);
    }


    @Override
    public OwnProfile getOwnProfile() {
        return null;
    }

    @Override
    public void login(String username, String password) throws LoginException, NetworkErrorException {
        NetworkConstants.setAuthorization(username, password);
        OwnProfile ownProfile = profileNetworkController.get();
        ownProfile.setPassword(password);
        ProfileDataStorage.setOwnProfile(ownProfile);

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
    public void setIncognito(boolean incognito) throws NetworkErrorException {
        OwnProfile ownProfile = ProfileDataStorage.getOwnProfile();
        ownProfile.setIncognito(incognito);
        try {
            profileNetworkController.put(ownProfile);
        } catch (UsernameAlreadyExistsException e) {
            //wont happen
        }
        ProfileDataStorage.setIncognito(incognito);
    }

    @Override
    public void insertNetworkController(ProfileNetworkControllerImpl profileNetworkController) {
        this.profileNetworkController = profileNetworkController;
    }


}
