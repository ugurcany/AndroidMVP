package com.accenture.androidmvp.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class Movie {

    @SerializedName("imdbID")
    public String imdbId;

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("Plot")
    public String plot;

    @SerializedName("Poster")
    public String poster;

}
