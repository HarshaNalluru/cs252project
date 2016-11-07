package com.example.harsha.receivesms;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by GUNDA ABHISHEK on 01-11-2016.
 */
public interface DataService {
    @POST("/insertdata.php")
    public void getEvents(
            @Query("messagesent") String message, Callback<APIResponse> cb
    );
}
