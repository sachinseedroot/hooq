package com.sachin.hooq.Network;


import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static String WEB_API_BASE_URL = "http://43.231.124.238/";

    public static WebService getWebService() {
        return new Retrofit.Builder().baseUrl(WEB_API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebService.class);
    }

}
