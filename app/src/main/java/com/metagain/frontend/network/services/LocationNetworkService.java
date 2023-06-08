package com.metagain.frontend.network.services;

import com.metagain.frontend.network.NetworkConstants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface LocationNetworkService {

    @PUT(NetworkConstants.UPDATE_LOCATION)
    public Call<Void> put(@Header("Authorization") String authorization, @Body double[] coordinates);

}
