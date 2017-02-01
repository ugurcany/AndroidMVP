package com.accenture.androidmvp.presenter.contract;

import com.accenture.androidmvp.view.contract.IMovieDetailView;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public interface IMovieDetailPresenter {

    void setView(IMovieDetailView view);

    void getMovieDetail(String imdbId);

}
