package com.accenture.androidmvp.model;

import com.accenture.androidmvp.model.pojo.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class MovieDetailInteractor implements Callback<Movie> {

    private RestApi restApi;
    private OnResponseListener<Movie> responseListener;

    public MovieDetailInteractor(RestApi restApi){
        this.restApi = restApi;
    }

    public void getMovieDetail(OnResponseListener<Movie> responseListener, String imdbId){
        this.responseListener = responseListener;

        restApi.getMovieDetail(imdbId, "short", "json").enqueue(this);
    }

    @Override
    public void onResponse(Call<Movie> call, Response<Movie> response) {
        if(response.isSuccessful()) {
            responseListener.onSuccess(response.body());
        }
        else{
            responseListener.onError("Service error!");
        }
    }

    @Override
    public void onFailure(Call<Movie> call, Throwable t) {
        responseListener.onError("Network error!");
    }

}
