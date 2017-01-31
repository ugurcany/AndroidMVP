package com.accenture.androidmvp.dagger.module;

import com.accenture.androidmvp.dagger.scope.AppScope;
import com.accenture.androidmvp.model.ApiInteractor;
import com.accenture.androidmvp.model.RestApi;
import com.accenture.androidmvp.model.pojo.Movie;
import com.accenture.androidmvp.model.pojo.MovieList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
@Module
public class ApiInteractorModule {

    @Provides
    @AppScope
    ApiInteractor<MovieList> apiInteractorMovieList(RestApi restApi){
        return new ApiInteractor<MovieList>(restApi);
    }

    @Provides
    @AppScope
    ApiInteractor<Movie> apiInteractorMovie(RestApi restApi){
        return new ApiInteractor<Movie>(restApi);
    }

}
