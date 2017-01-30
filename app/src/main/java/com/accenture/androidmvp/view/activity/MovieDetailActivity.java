package com.accenture.androidmvp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.accenture.androidmvp.App;
import com.accenture.androidmvp.R;
import com.accenture.androidmvp.model.pojo.Movie;
import com.accenture.androidmvp.presenter.MovieDetailPresenter;
import com.accenture.androidmvp.view.contract.IMovieDetailView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class MovieDetailActivity extends AppCompatActivity implements IMovieDetailView {

    @Inject
    MovieDetailPresenter presenter;

    @BindView(R.id.movieInfoText)
    TextView movieInfoText;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.errText)
    TextView errText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);

        App.injector().presenterComponent().inject(this);
        ButterKnife.bind(this);

        String imdbId = getIntent().getStringExtra("imdbId");

        presenter.getMovieDetail(this, imdbId);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        movieInfoText.setVisibility(View.GONE);
        errText.setVisibility(View.GONE);
    }

    @Override
    public void showSuccess(Movie movie) {
        movieInfoText.setText(
                movie.title + "\n" +
                movie.year + "\n" +
                movie.plot);

        progressBar.setVisibility(View.GONE);
        movieInfoText.setVisibility(View.VISIBLE);
        errText.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errMessage) {
        errText.setText(errMessage);

        progressBar.setVisibility(View.GONE);
        movieInfoText.setVisibility(View.GONE);
        errText.setVisibility(View.VISIBLE);
    }

}
