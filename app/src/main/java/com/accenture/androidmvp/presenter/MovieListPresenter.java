package com.accenture.androidmvp.presenter;

import com.accenture.androidmvp.App;
import com.accenture.androidmvp.model.MovieListInteractor;
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

    @Inject
    MovieListInteractor interactor;

    public MovieListPresenter(){
        App.injector().interactorComponent().inject(this);
    }

    @Override
    public void getMovieList(IMovieListView view, String searchKey){
        this.view = view;
        view.showLoading();

        interactor.getMovieList(this, searchKey);
    }


    @Override
    public void onSuccess(MovieList movieList) {
        view.showSuccess(movieList.movieList);
    }

    @Override
    public void onError(String errMessage) {
        view.showError(errMessage);
    }

}
