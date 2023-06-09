package com.metagain.frontend.network;

import com.metagain.frontend.network.services.FriendsNetworkService;
import com.metagain.frontend.network.services.LocationNetworkService;
import com.metagain.frontend.network.services.MeetingNetworkService;
import com.metagain.frontend.network.services.ProfileNetworkService;
import com.metagain.frontend.network.services.RequestNetworkService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkConstants {

    //public static final String URL = "https://metagain-backend-production.up.railway.app/";
    public static final String URL = "http://192.168.178.145:8080/";

    public static final String PROFILES = "profiles";

    public static final String REQUESTS = "requests";

    public static final String FRIENDS = "friends";

    public static final String UPDATE_LOCATION = "updates/location";

    public static final String MEETINGS = "meetings";

    private static final OkHttpClient httpClient = new OkHttpClient.Builder().build();



    public static String AUTHORIZATION = "";

    public static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build();


    public static ProfileNetworkService createProfileNetworkService() {
        return RETROFIT.create(ProfileNetworkService.class);
    }

    public static RequestNetworkService createRequestNetworkService() {
        return RETROFIT.create(RequestNetworkService.class);
    }

    public static FriendsNetworkService createFriendsNetworkService() {
        return RETROFIT.create(FriendsNetworkService.class);
    }

    public static LocationNetworkService createLocationNetworkService() {
        return RETROFIT.create(LocationNetworkService.class);
    }

    public static MeetingNetworkService createMeetingNetworkService() {
        return RETROFIT.create(MeetingNetworkService.class);
    }

    public static void setAuthorization(String username, String password) {

        AUTHORIZATION = "Basic " + Base64.getEncoder().encodeToString((username+":"+password).getBytes(StandardCharsets.UTF_8));

    }

    public static void removeAuthorization() {
        AUTHORIZATION = "";
    }




}
