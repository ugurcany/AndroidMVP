package com.accenture.androidmvp.dagger.module;

import android.content.Context;

import com.accenture.androidmvp.dagger.scope.AppScope;
import com.accenture.androidmvp.model.ApiInteractor;
import com.accenture.androidmvp.model.LocalDbInteractor;
import com.accenture.androidmvp.model.RestApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
@Module
public class InteractorModule {

    private Context context;

    public InteractorModule(Context context){
        this.context = context;
    }

    @Provides
    @AppScope
    ApiInteractor apiInteractor(RestApi restApi){
        return new ApiInteractor(restApi);
    }

    @Provides
    @AppScope
    LocalDbInteractor localDbInteractor(){
        return new LocalDbInteractor(context);
    }

}
