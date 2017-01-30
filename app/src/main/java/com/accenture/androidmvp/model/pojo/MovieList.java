package com.accenture.androidmvp.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class MovieList {

    @SerializedName("Search")
    public List<Movie> movieList;

}
