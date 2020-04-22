package com.fitness.android.myapplication.Retrofit;

import com.fitness.android.myapplication.pojo.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {
   @GET("movie?api_key=f37235d3c3634c4c4d512f8245131ee2")
    Call<Results> getResults(@Query("") String query);
}
