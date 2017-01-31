package com.accenture.androidmvp.dagger.component;

import com.accenture.androidmvp.dagger.module.ApiInteractorModule;
import com.accenture.androidmvp.dagger.module.LocalDbInteractorModule;
import com.accenture.androidmvp.dagger.module.RestModule;
import com.accenture.androidmvp.dagger.scope.AppScope;
import com.accenture.androidmvp.presenter.MovieDetailPresenter;
import com.accenture.androidmvp.presenter.MovieListPresenter;

import dagger.Component;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
@AppScope
@Component(modules = {ApiInteractorModule.class, LocalDbInteractorModule.class, RestModule.class})
public interface PresenterInjector {

    void inject(MovieListPresenter presenter);
    void inject(MovieDetailPresenter presenter);

}
