package com.metagain.frontend.network;

import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.model.Profile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProfileNetworkController {

    @POST(NetworkConstants.PROFILES)
    public Call<Void> createAccount(@Body OwnProfile ownProfile);

}
