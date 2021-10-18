package com.example.projecttest.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    public static ApiEndpoint Api() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create();

        Retrofit retrofitGMot = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofitGMot.create(ApiEndpoint.class);
    }

    private static OkHttpClient getClient() {
        int timesOut = 1;
        return new OkHttpClient.Builder()
                .connectTimeout(timesOut, TimeUnit.MINUTES)
                .readTimeout(timesOut, TimeUnit.MINUTES)
                .writeTimeout(timesOut, TimeUnit.MINUTES)
                .addInterceptor(getLoggingInterceptor())
                .build();
    }

    private static HttpLoggingInterceptor.Level getInterceptorLevel() {
        return HttpLoggingInterceptor.Level.BODY;
    }

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(getInterceptorLevel());
        return httpLoggingInterceptor;
    }
}
