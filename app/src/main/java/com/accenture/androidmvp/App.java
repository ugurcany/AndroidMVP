package com.accenture.androidmvp;

import android.app.Application;

import com.accenture.androidmvp.dagger.InjectorFactory;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class App extends Application {

    private static InjectorFactory injectorFactory;

    @Override
    public void onCreate() {
        super.onCreate();

        injectorFactory = new InjectorFactory(this);
    }

    public static InjectorFactory injectorFactory(){
        return injectorFactory;
    }

}
