package com.metagain.frontend.network.services;

import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.network.NetworkConstants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ProfileNetworkService {

    @POST(NetworkConstants.PROFILES)
    public Call<Void> post(@Body OwnProfile ownProfile);

    @GET(NetworkConstants.PROFILES)
    public Call<OwnProfile> get(@Header("Authorization") String authorization);

}
