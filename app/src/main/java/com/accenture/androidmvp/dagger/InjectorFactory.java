package com.accenture.androidmvp.dagger;

import android.app.Application;

import com.accenture.androidmvp.dagger.component.DaggerPresenterInjector;
import com.accenture.androidmvp.dagger.component.DaggerViewInjector;
import com.accenture.androidmvp.dagger.component.PresenterInjector;
import com.accenture.androidmvp.dagger.component.ViewInjector;
import com.accenture.androidmvp.dagger.module.ApiInteractorModule;
import com.accenture.androidmvp.dagger.module.LocalDbInteractorModule;
import com.accenture.androidmvp.dagger.module.PresenterModule;
import com.accenture.androidmvp.dagger.module.RestModule;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class InjectorFactory {

    private ViewInjector viewInjector;
    private PresenterInjector presenterInjector;

    public InjectorFactory(Application application){
        viewInjector = DaggerViewInjector.builder()
                .presenterModule(new PresenterModule())
                .build();

        presenterInjector = DaggerPresenterInjector.builder()
                .apiInteractorModule(new ApiInteractorModule())
                .localDbInteractorModule(new LocalDbInteractorModule(application.getApplicationContext()))
                .restModule(new RestModule())
                .build();
    }

    public ViewInjector viewInjector(){
        return viewInjector;
    }

    public PresenterInjector presenterInjector(){
        return presenterInjector;
    }

}
