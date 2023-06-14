package com.metagain.frontend.network.services;

import com.metagain.frontend.model.Meeting;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.network.NetworkConstants;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MeetingNetworkService {

    @GET(NetworkConstants.MEETINGS)
    public Call<List<Meeting>> get(@Header("Authorization") String authorization);

    @DELETE(NetworkConstants.MEETINGS + "/{id}")
    public Call<Void> delete(@Header("Authorization") String authorization, @Path("id") UUID meetingID);

    @PUT(NetworkConstants.MEETINGS)
    public Call<Void> put(@Header("Authorization") String authorization, @Body Meeting meeting);

}
