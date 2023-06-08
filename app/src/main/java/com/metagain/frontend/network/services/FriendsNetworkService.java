package com.metagain.frontend.network.services;

import com.metagain.frontend.model.Friends;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.network.NetworkConstants;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface FriendsNetworkService {

    @GET(NetworkConstants.FRIENDS)
    public Call<List<Friends>> get(@Header("Authorization") String authorization);

    @DELETE(NetworkConstants.FRIENDS + "/{id}")
    public Call<Void> delete(@Header("Authorization") String authorization,@Path("id") UUID friendsID);

}
