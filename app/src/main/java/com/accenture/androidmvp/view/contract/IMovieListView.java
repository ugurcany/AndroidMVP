package com.accenture.androidmvp.view.contract;

import com.accenture.androidmvp.model.pojo.Movie;

import java.util.List;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public interface IMovieListView {

    void showLoading();

    void showSuccess(List<Movie> movieList);

    void showError(String errMessage);

}
