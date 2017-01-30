package com.accenture.androidmvp.presenter;

import com.accenture.androidmvp.App;
import com.accenture.androidmvp.model.MovieDetailInteractor;
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

    @Inject
    MovieDetailInteractor interactor;

    public MovieDetailPresenter(){
        App.injector().interactorComponent().inject(this);
    }

    @Override
    public void getMovieDetail(IMovieDetailView view, String imdbId) {
        this.view = view;
        view.showLoading();

        interactor.getMovieDetail(this, imdbId);
    }


    @Override
    public void onSuccess(Movie movie) {
        view.showSuccess(movie);
    }

    @Override
    public void onError(String errMessage) {
        view.showError(errMessage);
    }

}
