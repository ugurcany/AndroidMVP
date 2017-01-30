package com.accenture.androidmvp.dagger.module;

import com.accenture.androidmvp.dagger.scope.AppScope;
import com.accenture.androidmvp.presenter.MovieDetailPresenter;
import com.accenture.androidmvp.presenter.MovieListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
@Module
public class PresenterModule {

    @Provides
    @AppScope
    MovieListPresenter movieListPresenter(){
        return new MovieListPresenter();
    }

    @Provides
    @AppScope
    MovieDetailPresenter movieDetailPresenter(){
        return new MovieDetailPresenter();
    }

}
