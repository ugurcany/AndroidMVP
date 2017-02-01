package com.accenture.androidmvp.util;

import android.app.Activity;
import android.content.Intent;

import com.accenture.androidmvp.view.activity.MovieDetailActivity;

/**
 * Created by ugurcan.yildirim on 1.02.2017.
 */
public class NavigationManager {

    public static void goToMovieDetail(Activity activity, String imdbId){
        Intent intent = new Intent(activity, MovieDetailActivity.class);
        intent.putExtra("imdbId", imdbId);
        activity.startActivity(intent);
    }

}
