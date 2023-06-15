package com.metagain.frontend.network.controller.implementations;

import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.model.Friends;
import com.metagain.frontend.network.CallExecutor;
import com.metagain.frontend.network.controller.FriendsNetworkController;
import com.metagain.frontend.network.NetworkConstants;
import com.metagain.frontend.network.services.FriendsNetworkService;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;

public class FriendsNetworkControllerImpl implements FriendsNetworkController {


    private FriendsNetworkService friendsNetworkService = NetworkConstants.createFriendsNetworkService();

    private CallExecutor callExecutor = new CallExecutor();

    @Override
    public List<Friends> get() throws NetworkErrorException {
        Call<List<Friends>> call = friendsNetworkService.get(NetworkConstants.AUTHORIZATION);
        Response<List<Friends>> response = callExecutor.execute(call);
        if (response == null) {
            throw new NetworkErrorException();
        }

        return (List<Friends>) response.body();
    }

    @Override
    public void delete(UUID friendsID) throws NetworkErrorException {
        Call<Void> call = friendsNetworkService.delete(NetworkConstants.AUTHORIZATION, friendsID);
        Response<Void> response = callExecutor.execute(call); //TODO ?
        if (response == null) {
            throw new NetworkErrorException();
        }
    }


}
