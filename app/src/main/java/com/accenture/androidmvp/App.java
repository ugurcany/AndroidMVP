package com.accenture.androidmvp;

import android.app.Application;

import com.accenture.androidmvp.dagger.Injector;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class App extends Application {

    private static Injector injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = new Injector(this);
    }

    public static Injector injector(){
        return injector;
    }

}
