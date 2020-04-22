package com.fitness.android.myapplication.Retrofit;


import com.fitness.android.myapplication.pojo.Results;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/discover/";
    private MovieInterface movieInterface;
    private static MoviesClient INSTANCE;

    public MoviesClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        movieInterface = retrofit.create(MovieInterface.class);

    }

    public static MoviesClient getINSTANCE() {

        if (INSTANCE == null){
            INSTANCE = new MoviesClient();
        }
        return INSTANCE;
    }

    public Call<Results> getMovies(String query){
       return movieInterface.getResults(query);
    }
}
