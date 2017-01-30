package com.accenture.androidmvp.dagger.module;

import com.accenture.androidmvp.dagger.scope.AppScope;
import com.accenture.androidmvp.model.MovieDetailInteractor;
import com.accenture.androidmvp.model.MovieListInteractor;
import com.accenture.androidmvp.model.RestApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
@Module
public class InteractorModule {

    @Provides
    @AppScope
    MovieListInteractor movieListInteractor(RestApi restApi){
        return new MovieListInteractor(restApi);
    }

    @Provides
    @AppScope
    MovieDetailInteractor movieDetailInteractor(RestApi restApi){
        return new MovieDetailInteractor(restApi);
    }

}
