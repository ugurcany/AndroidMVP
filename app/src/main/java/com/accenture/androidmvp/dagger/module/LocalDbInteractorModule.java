package com.accenture.androidmvp.dagger.module;

import android.content.Context;

import com.accenture.androidmvp.dagger.scope.AppScope;
import com.accenture.androidmvp.model.LocalDbInteractor;
import com.accenture.androidmvp.model.pojo.Movie;
import com.accenture.androidmvp.model.pojo.MovieList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
@Module
public class LocalDbInteractorModule {

    private Context context;

    public LocalDbInteractorModule(Context context){
        this.context = context;
    }

    @Provides
    @AppScope
    LocalDbInteractor<MovieList> localDbInteractorMovieList(){
        return new LocalDbInteractor<MovieList>(context, "movie_list");
    }

    @Provides
    @AppScope
    LocalDbInteractor<Movie> localDbInteractorMovie(){
        return new LocalDbInteractor<Movie>(context, "movie");
    }

}
