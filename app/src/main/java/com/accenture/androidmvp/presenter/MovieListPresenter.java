package com.accenture.androidmvp.presenter;

import com.accenture.androidmvp.App;
import com.accenture.androidmvp.model.ApiInteractor;
import com.accenture.androidmvp.model.LocalDbInteractor;
import com.accenture.androidmvp.model.OnResponseListener;
import com.accenture.androidmvp.model.pojo.MovieList;
import com.accenture.androidmvp.presenter.contract.IMovieListPresenter;
import com.accenture.androidmvp.view.contract.IMovieListView;

import javax.inject.Inject;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class MovieListPresenter implements IMovieListPresenter, OnResponseListener<MovieList> {

    private IMovieListView view;
    private String key;

    @Inject
    ApiInteractor apiInteractor;

    @Inject
    LocalDbInteractor localDbInteractor;

    public MovieListPresenter(){
        App.injector().interactorComponent().inject(this);

        localDbInteractor.setTableName("movie_list");
    }

    @Override
    public void getMovieList(IMovieListView view, String searchKey){
        this.key = searchKey;
        this.view = view;
        view.showLoading();

        localDbInteractor.getObject(this, searchKey, MovieList.class);

        apiInteractor.sendRequest(this, apiInteractor.restApi().getMovieList(searchKey, "json"));
    }

    @Override
    public void onLocallyExist(MovieList movieList) {
        view.showSuccess(movieList.movieList);
    }

    @Override
    public void onApiSuccess(MovieList movieList) {
        localDbInteractor.putObject(key, movieList);

        view.showSuccess(movieList.movieList);
    }

    @Override
    public void onApiError(String errMessage) {
        view.showError(errMessage);
    }

}
