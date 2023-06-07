package com.metagain.frontend.network.services;

import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.model.Request;
import com.metagain.frontend.network.NetworkConstants;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestNetworkService {

    @POST(NetworkConstants.REQUESTS)
    public Call<Void> post(@Header("Authorization") String authorization, @Body Request request);

    @GET(NetworkConstants.REQUESTS)
    public Call<List<Request>> get(@Header("Authorization") String authorization);

    @DELETE(NetworkConstants.REQUESTS + "/{id}")
    public Call<Void> delete(@Header("Authorization") String authorization,@Path("id")  UUID requestID);

    @PATCH(NetworkConstants.REQUESTS + "/{id}")
    public Call<Void> patch(@Header("Authorization") String authorization, @Path("id") UUID requestID);

}
