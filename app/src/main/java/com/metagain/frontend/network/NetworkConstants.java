package com.metagain.frontend.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkConstants {

    public static final String URL = "https://metagain-backend-production.up.railway.app/";

    public static final String PROFILES = "profiles";

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();


    public static ProfileNetworkController getProfileNetworkController() {
        return RETROFIT.create(ProfileNetworkController.class);
    }

}
