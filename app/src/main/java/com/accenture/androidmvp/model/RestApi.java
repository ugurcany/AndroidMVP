package com.accenture.androidmvp.model;

import com.accenture.androidmvp.model.pojo.Movie;
import com.accenture.androidmvp.model.pojo.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public interface RestApi {

    @GET("/")
    Call<MovieList> getMovieList(
            @Query("s") String searchKey,
            @Query("r") String responseType
    );

    @GET("/")
    Call<Movie> getMovieDetail(
            @Query("i") String imdbId,
            @Query("plot") String plotLength,
            @Query("r") String responseType
    );

}
