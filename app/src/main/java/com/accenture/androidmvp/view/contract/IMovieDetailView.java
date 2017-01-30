package com.accenture.androidmvp.view.contract;

import com.accenture.androidmvp.model.pojo.Movie;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public interface IMovieDetailView {

    void showLoading();

    void showSuccess(Movie movie);

    void showError(String errMessage);

}
