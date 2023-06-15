package com.metagain.frontend.network.controller.implementations;

import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.network.CallExecutor;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.controller.LocationNetworkController;
import com.metagain.frontend.network.services.LocationNetworkService;

import retrofit2.Call;
import retrofit2.Response;

public class LocationNetworkControllerImpl implements LocationNetworkController {

    LocationNetworkService locationNetworkService = NetworkConstants.createLocationNetworkService();

    private String authorizationLastUser;

    private CallExecutor callExecutor = new CallExecutor();

    public LocationNetworkControllerImpl(String authorizationLastUser) {
        this.authorizationLastUser = authorizationLastUser;
    }

    @Override
    public void put(double[] location) throws NetworkErrorException {
        Call<Void> call = locationNetworkService.put(authorizationLastUser, location);
        Response<Void> response = callExecutor.execute(call); //TODO ?
        if (response == null) {
            throw new NetworkErrorException();
        }

    }

    public void putOnDestroy(double[] location) throws NetworkErrorException {
        put(location);
        authorizationLastUser = null;
    }

}
