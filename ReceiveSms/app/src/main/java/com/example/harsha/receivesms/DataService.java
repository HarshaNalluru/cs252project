package com.example.harsha.receivesms;


import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by harsha on 31/10/16.
 */
public interface DataService {
    @POST("/cs252/insertdata.php")
    public void insertdetails(
            @Query("message") String message, Callback<APIResponse> cb
    );
}
