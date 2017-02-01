package com.accenture.androidmvp.model;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public interface OnResponseListener<T> {

    void onLocallyExist(String key, T response);

    void onApiSuccess(String key, T response);

    void onApiError(String key, String errMessage);

}
