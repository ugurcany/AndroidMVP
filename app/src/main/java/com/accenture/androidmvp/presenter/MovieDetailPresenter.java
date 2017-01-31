package com.accenture.androidmvp.presenter;

import com.accenture.androidmvp.App;
import com.accenture.androidmvp.model.ApiInteractor;
import com.accenture.androidmvp.model.LocalDbInteractor;
import com.accenture.androidmvp.model.OnResponseListener;
import com.accenture.androidmvp.model.pojo.Movie;
import com.accenture.androidmvp.presenter.contract.IMovieDetailPresenter;
import com.accenture.androidmvp.view.contract.IMovieDetailView;

import javax.inject.Inject;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class MovieDetailPresenter implements IMovieDetailPresenter, OnResponseListener<Movie> {

    private IMovieDetailView view;
    private String key;

    @Inject
    ApiInteractor<Movie> apiInteractor;

    @Inject
    LocalDbInteractor<Movie> localDbInteractor;

    public MovieDetailPresenter(){
        App.injectorFactory().presenterInjector().inject(this);
    }

    @Override
    public void getMovieDetail(IMovieDetailView view, String imdbId) {
        this.key = imdbId;
        this.view = view;
        view.showLoading();

        localDbInteractor.getObject(this, key, Movie.class);

        apiInteractor.sendRequest(this, apiInteractor.restApi().getMovieDetail(imdbId, "short", "json"));
    }

    @Override
    public void onLocallyExist(Movie movie) {
        view.showSuccess(movie);
    }

    @Override
    public void onApiSuccess(Movie movie) {
        localDbInteractor.putObject(key, movie);

        view.showSuccess(movie);
    }

    @Override
    public void onApiError(String errMessage) {
        view.showError(errMessage);
    }

}
