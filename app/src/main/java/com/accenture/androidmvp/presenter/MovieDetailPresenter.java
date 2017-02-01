package com.accenture.androidmvp.presenter;

import com.accenture.androidmvp.App;
import com.accenture.androidmvp.model.ApiInteractor;
import com.accenture.androidmvp.model.LocalDbInteractor;
import com.accenture.androidmvp.model.OnResponseListener;
import com.accenture.androidmvp.model.pojo.Movie;
import com.accenture.androidmvp.presenter.contract.IMovieDetailPresenter;
import com.accenture.androidmvp.view.contract.IMovieDetailView;

import javax.inject.Inject;

import retrofit2.Call;

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
    public void setView(IMovieDetailView view) {
        this.view = view;
    }

    @Override
    public void getMovieDetail(String imdbId) {
        view.showLoading();

        Call<Movie> call = apiInteractor.restApi().getMovieDetail(imdbId, "short", "json");
        key = call.request().url().toString();

        localDbInteractor.getObject(this, key, Movie.class);
        apiInteractor.sendRequest(this, call);
    }

    @Override
    public void onLocallyExist(String key, Movie movie) {
        if(!key.equals(this.key) || view == null) return;

        view.showSuccess(movie);
    }

    @Override
    public void onApiSuccess(String key, Movie movie) {
        if(!key.equals(this.key) || view == null) return;

        localDbInteractor.putObject(key, movie);

        view.showSuccess(movie);
    }

    @Override
    public void onApiError(String key, String errMessage) {
        if(!key.equals(this.key) || view == null) return;

        view.showError(errMessage);
    }

}
