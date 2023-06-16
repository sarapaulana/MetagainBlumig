package com.metagain.frontend.network.controller.implementations;

import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.InvalidUsernameException;
import com.metagain.frontend.exceptions.LoginException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.UsernameAlreadyExistsException;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.network.CallExecutor;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.controller.ProfileNetworkController;
import com.metagain.frontend.network.services.ProfileNetworkService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ProfileNetworkControllerImpl implements ProfileNetworkController {

    private ProfileNetworkService profileNetworkService = NetworkConstants.createProfileNetworkService();

    private CallExecutor callExecutor = new CallExecutor();

    @Override
    public void post(OwnProfile ownProfile) throws NetworkErrorException, UsernameAlreadyExistsException {
        Call<Void> call = profileNetworkService.post(ownProfile);
        Response<Void> response = callExecutor.execute(call);
        if (response == null) {
            throw new NetworkErrorException();
        } else if (response.code() == 409) {
            throw new UsernameAlreadyExistsException();
        }


    }

    @Override
    public OwnProfile get() throws NetworkErrorException, LoginException {
        Call<OwnProfile> call = profileNetworkService.get(NetworkConstants.AUTHORIZATION);

        Response<OwnProfile> response = callExecutor.execute(call);

        if (response == null) {
            throw new NetworkErrorException();
        } else if(response.code() == 401) {
            throw new LoginException();
        }

        OwnProfile ownProfile = response.body();
        return ownProfile;
    }

    @Override
    public void delete() throws  NetworkErrorException {
        Call<Void> call = profileNetworkService.delete(NetworkConstants.AUTHORIZATION);

        Response<Void> response = callExecutor.execute(call);
        if (response == null) {
            throw new NetworkErrorException();
        }
    }

    @Override
    public void put(OwnProfile ownProfile) throws UsernameAlreadyExistsException, NetworkErrorException {
        Call<Void> call = profileNetworkService.put(NetworkConstants.AUTHORIZATION, ownProfile);

        Response<Void> response = callExecutor.execute(call);

        if (response == null) {
            throw new NetworkErrorException();
        } else if (response.code() == 409) {
            throw new UsernameAlreadyExistsException();
        }

        //TODO Exceptions (edit Username)
    }

    @Override
    public void insertNetworkService(ProfileNetworkService profileNetworkService) {
        this.profileNetworkService = profileNetworkService;
    }

}
