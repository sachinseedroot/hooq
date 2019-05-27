package com.sachin.hooq.Network;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface WebService {

    @GET()
    Observable<Object> getMovieData(@Url String url);
}
