package com.metagain.frontend.controll.implementations;

import android.util.Log;

import com.metagain.frontend.controll.ProfileController;
import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.InvalidUsernameException;
import com.metagain.frontend.exceptions.LoginException;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.model.Profile;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.ProfileNetworkController;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileControllerImpl implements ProfileController {

    private final ProfileNetworkController profileNetworkController = NetworkConstants.getProfileNetworkController();

    private int code;

    @Override
    public void createAccount(OwnProfile ownProfile) throws InvalidEmailException, InvalidUsernameException {
        Call<Void> callRegister = profileNetworkController.createAccount(ownProfile);
        System.out.println("foooooo");
        callRegister.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("aaaaaaaa");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Fehler: " + t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void editProfile(OwnProfile ownProfile) throws InvalidUsernameException, InvalidEmailException {

    }

    @Override
    public OwnProfile getOwnProfile() {
        return null;
    }

    @Override
    public void login(String username, String password) throws LoginException {
        throw new LoginException();
    }

    @Override
    public void insertNetworkController(ProfileNetworkController profileNetworkController) {

    }
}
