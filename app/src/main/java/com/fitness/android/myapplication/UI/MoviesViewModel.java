package com.fitness.android.myapplication.UI;

import com.fitness.android.myapplication.Retrofit.MoviesClient;
import com.fitness.android.myapplication.pojo.Results;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesViewModel extends ViewModel {

    MutableLiveData<Results> moviesMutable = new MutableLiveData<>();
    MutableLiveData<String> error = new MutableLiveData<>();

    public void getMoves(String query){

        MoviesClient.getINSTANCE().getMovies(query).enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                moviesMutable.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                error.setValue(t.toString());
            }
        });

    }
}
