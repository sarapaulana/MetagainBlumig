package com.metagain.frontend.network.controller.implementations;

import com.metagain.frontend.exceptions.InvalidUsernameException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.NotFriendsException;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.network.CallExecutor;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.controller.RequestNetworkController;
import com.metagain.frontend.network.services.RequestNetworkService;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;

public class RequestNetworkControllerImpl implements  RequestNetworkController {


    private RequestNetworkService requestNetworkService = NetworkConstants.createRequestNetworkService();

    private CallExecutor callExecutor = new CallExecutor();

    @Override
    public void post(Request request) throws NotFriendsException, NetworkErrorException, InvalidUsernameException {

        Call<Void> call = requestNetworkService.post(NetworkConstants.AUTHORIZATION, request);

        Response<Void> response = callExecutor.execute(call);

        if (response == null) {
            throw new NetworkErrorException();
        } else if (response.code() == 409) {
            throw new NotFriendsException();
        } else if (response.code() == 500) {
            throw new InvalidUsernameException();
        }

    }

    @Override
    public List<Request> get() throws NetworkErrorException {
        Call<List<Request>> call = requestNetworkService.get(NetworkConstants.AUTHORIZATION);

        Response<List<Request>> response = callExecutor.execute(call);

        if (response == null) {
            throw new NetworkErrorException();
        }

        return (List<Request>) response.body();
    }

    @Override
    public void delete(UUID requestID) throws NetworkErrorException {

        Call<Void> call = requestNetworkService.delete(NetworkConstants.AUTHORIZATION, requestID);
        Response<Void> response = callExecutor.execute(call); //TODO ?

        if (response == null) {
            throw new NetworkErrorException();
        }
    }

    @Override
    public void patch(UUID requestID) throws NetworkErrorException {
        Call<Void> call = requestNetworkService.patch(NetworkConstants.AUTHORIZATION, requestID);
        Response<Void> response = callExecutor.execute(call); //TODO ?

        if (response == null) {
            throw new NetworkErrorException();
        }
    }


}
