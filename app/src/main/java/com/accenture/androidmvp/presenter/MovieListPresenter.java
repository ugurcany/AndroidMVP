package com.accenture.androidmvp.presenter;

import com.accenture.androidmvp.App;
import com.accenture.androidmvp.model.ApiInteractor;
import com.accenture.androidmvp.model.LocalDbInteractor;
import com.accenture.androidmvp.model.OnResponseListener;
import com.accenture.androidmvp.model.pojo.MovieList;
import com.accenture.androidmvp.presenter.contract.IMovieListPresenter;
import com.accenture.androidmvp.view.contract.IMovieListView;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class MovieListPresenter implements IMovieListPresenter, OnResponseListener<MovieList> {

    private IMovieListView view;
    private String key;

    @Inject
    ApiInteractor<MovieList> apiInteractor;

    @Inject
    LocalDbInteractor<MovieList> localDbInteractor;

    public MovieListPresenter(){
        App.injectorFactory().presenterInjector().inject(this);
    }

    @Override
    public void setView(IMovieListView view) {
        this.view = view;
    }

    @Override
    public void getMovieList(String searchKey){
        view.showLoading();

        Call<MovieList> call = apiInteractor.restApi().getMovieList(searchKey, "json");
        key = call.request().url().toString();

        localDbInteractor.getObject(this, key, MovieList.class);
        apiInteractor.sendRequest(this, call);
    }

    @Override
    public void onLocallyExist(String key, MovieList movieList) {
        if(!key.equals(this.key) || view == null) return;

        view.showSuccess(movieList.movieList);
    }

    @Override
    public void onApiSuccess(String key, MovieList movieList) {
        if(!key.equals(this.key) || view == null) return;

        localDbInteractor.putObject(key, movieList);

        if(movieList.movieList == null || movieList.movieList.isEmpty()) {
            view.showError("No movie found!");
        }
        else {
            view.showSuccess(movieList.movieList);
        }
    }

    @Override
    public void onApiError(String key, String errMessage) {
        if(!key.equals(this.key) || view == null) return;

        view.showError(errMessage);
    }

}
