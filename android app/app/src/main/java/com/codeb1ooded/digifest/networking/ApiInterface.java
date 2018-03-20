package com.codeb1ooded.digifest.networking;

import com.codeb1ooded.digifest.BlockData;
import com.codeb1ooded.digifest.EmptyClass;
import com.codeb1ooded.digifest.RecordModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by megha on 03/12/17.
 */

public interface ApiInterface {

    @POST("/sfg/home/")
    Call<EmptyClass> addBlock(@Body JsonObject jsonObject);

    @GET("/get_details")
    Call<RecordModel> getDetails(@Query("username") String username);

}
