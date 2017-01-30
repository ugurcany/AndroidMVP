package com.accenture.androidmvp.dagger;

import android.app.Application;

import com.accenture.androidmvp.dagger.component.DaggerInteractorComponent;
import com.accenture.androidmvp.dagger.component.DaggerPresenterComponent;
import com.accenture.androidmvp.dagger.component.InteractorComponent;
import com.accenture.androidmvp.dagger.component.PresenterComponent;
import com.accenture.androidmvp.dagger.module.InteractorModule;
import com.accenture.androidmvp.dagger.module.PresenterModule;
import com.accenture.androidmvp.dagger.module.RestModule;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class Injector {

    private PresenterComponent presenterComponent;
    private InteractorComponent interactorComponent;

    public Injector(Application application){
        presenterComponent = DaggerPresenterComponent.builder()
                .presenterModule(new PresenterModule())
                .build();

        interactorComponent = DaggerInteractorComponent.builder()
                .interactorModule(new InteractorModule())
                .restModule(new RestModule())
                .build();
    }

    public PresenterComponent presenterComponent(){
        return presenterComponent;
    }

    public InteractorComponent interactorComponent(){
        return interactorComponent;
    }

}
