package com.accenture.androidmvp.presenter.contract;

import com.accenture.androidmvp.view.contract.IMovieListView;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public interface IMovieListPresenter {

    void setView(IMovieListView view);

    void getMovieList(String searchKey);

}
