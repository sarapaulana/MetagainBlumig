package com.metagain.frontend.network;

import com.metagain.frontend.network.services.ProfileNetworkService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkConstants {

    public static final String URL = "https://metagain-backend-production.up.railway.app/";

    public static final String PROFILES = "profiles";

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static String AUTHORIZATION = "";

    public static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();


    public static ProfileNetworkService getProfileNetworkController() {
        return RETROFIT.create(ProfileNetworkService.class);
    }

    public static void setAuthorization(String username, String password) {

        AUTHORIZATION = "Basic " + Base64.getEncoder().encodeToString((username+":"+password).getBytes(StandardCharsets.UTF_8));

    }

}
