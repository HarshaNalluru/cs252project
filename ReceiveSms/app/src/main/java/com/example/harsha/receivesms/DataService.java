package com.example.harsha.receivesms;

import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by GUNDA ABHISHEK on 01-11-2016.
 */
public interface DataService {
    @POST("/insertdata.php")
    public void senddata(
            @Query("message") String message, Callback<APIResponse> cb
    );
}
