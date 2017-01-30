package com.accenture.androidmvp.model;

import com.accenture.androidmvp.model.pojo.MovieList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class MovieListInteractor implements Callback<MovieList> {

    private RestApi restApi;
    private OnResponseListener<MovieList> responseListener;

    public MovieListInteractor(RestApi restApi){
        this.restApi = restApi;
    }

    public void getMovieList(OnResponseListener<MovieList> responseListener, String searchKey){
        this.responseListener = responseListener;

        restApi.getMovieList(searchKey, "json").enqueue(this);
    }

    @Override
    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
        if(response.isSuccessful()) {
            responseListener.onSuccess(response.body());
        }
        else{
            responseListener.onError("Service error!");
        }
    }

    @Override
    public void onFailure(Call<MovieList> call, Throwable t) {
        responseListener.onError("Network error!");
    }

}
