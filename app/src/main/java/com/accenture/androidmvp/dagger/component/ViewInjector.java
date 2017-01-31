package com.accenture.androidmvp.dagger.component;

import com.accenture.androidmvp.dagger.module.PresenterModule;
import com.accenture.androidmvp.dagger.scope.AppScope;
import com.accenture.androidmvp.view.activity.MovieDetailActivity;
import com.accenture.androidmvp.view.activity.MovieListActivity;

import dagger.Component;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
@AppScope
@Component(modules = {PresenterModule.class})
public interface ViewInjector {

    void inject(MovieListActivity view);
    void inject(MovieDetailActivity view);

}
