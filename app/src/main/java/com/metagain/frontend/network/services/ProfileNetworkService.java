package com.metagain.frontend.network.services;

import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.network.NetworkConstants;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProfileNetworkService {

    @POST(NetworkConstants.PROFILES)
    public Call<Void> post(@Body OwnProfile ownProfile);

    @GET(NetworkConstants.PROFILES)
    public Call<OwnProfile> get(@Header("Authorization") String authorization);

    @DELETE(NetworkConstants.PROFILES)
    public Call<Void> delete(@Header("Authorization") String authorization);

}
