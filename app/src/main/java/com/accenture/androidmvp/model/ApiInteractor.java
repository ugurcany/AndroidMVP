package com.accenture.androidmvp.model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class ApiInteractor<T> implements Callback<T> {

    private RestApi restApi;
    private OnResponseListener<T> responseListener;

    public ApiInteractor(RestApi restApi){
        this.restApi = restApi;
    }

    public RestApi restApi(){
        return restApi;
    }

    public void sendRequest(OnResponseListener<T> responseListener, Call<T> call){
        this.responseListener = responseListener;

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()) {
            responseListener.onApiSuccess(response.body());
        }
        else{
            responseListener.onApiError("Service error!");
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        responseListener.onApiError("Network error!");
    }

}
