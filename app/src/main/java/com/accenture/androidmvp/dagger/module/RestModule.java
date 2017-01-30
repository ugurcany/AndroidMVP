package com.accenture.androidmvp.dagger.module;

import com.accenture.androidmvp.BuildConfig;
import com.accenture.androidmvp.dagger.scope.AppScope;
import com.accenture.androidmvp.model.RestApi;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
@Module
public class RestModule {

    private final String BASE_URL = "http://www.omdbapi.com/";

    @Provides
    @AppScope
    OkHttpClient httpClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);

        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(interceptor);
        }

        return clientBuilder.build();
    }

    @Provides
    @AppScope
    Retrofit retrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    RestApi restApi(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }

}
