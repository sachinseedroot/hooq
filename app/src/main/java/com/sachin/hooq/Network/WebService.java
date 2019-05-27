package com.sachin.hooq.Network;


import io.reactivex.Observable;
import retrofit2.http.GET;


public interface WebService {

    @GET("matrix/iplookupmobile")
    Observable<Object> getMoviesList();

    @GET("matrix/iplookupmobile")
    Observable<Object> getMovieDetail(String key);
}
