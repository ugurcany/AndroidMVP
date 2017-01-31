package com.accenture.androidmvp.model;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public interface OnResponseListener<T> {

    void onLocallyExist(T response);

    void onApiSuccess(T response);

    void onApiError(String errMessage);

}
