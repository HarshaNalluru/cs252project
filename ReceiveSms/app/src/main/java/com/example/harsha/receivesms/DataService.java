package com.example.harsha.receivesms;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by GUNDA ABHISHEK on 01-11-2016.
 */
public interface DataService {
    @POST("/login2.php")
    public void getEvents(
            @Query("username") String gen,@Query("password") String password, Callback<APIResponse> cb
    );
}
