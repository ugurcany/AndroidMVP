package com.accenture.androidmvp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.accenture.androidmvp.App;
import com.accenture.androidmvp.R;
import com.accenture.androidmvp.model.pojo.Movie;
import com.accenture.androidmvp.presenter.MovieDetailPresenter;
import com.accenture.androidmvp.view.contract.IMovieDetailView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class MovieDetailActivity extends AppCompatActivity implements IMovieDetailView {

    @Inject
    MovieDetailPresenter presenter;

    @BindView(R.id.infoContainer)
    LinearLayout infoContainer;

    @BindView(R.id.poster)
    ImageView poster;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.year)
    TextView year;

    @BindView(R.id.plot)
    TextView plot;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.errText)
    TextView errText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);

        App.injectorFactory().viewInjector().inject(this);
        ButterKnife.bind(this);

        presenter.setView(this);

        String imdbId = getIntent().getStringExtra("imdbId");

        presenter.getMovieDetail(imdbId);
    }

    @Override
    protected void onDestroy() {
        presenter.setView(null);
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        infoContainer.setVisibility(View.GONE);
        errText.setVisibility(View.GONE);
    }

    @Override
    public void showSuccess(Movie movie) {
        title.setText(movie.title);
        year.setText(movie.year);
        plot.setText(movie.plot);
        Picasso.with(this).load(movie.poster).into(poster);

        progressBar.setVisibility(View.GONE);
        infoContainer.setVisibility(View.VISIBLE);
        errText.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errMessage) {
        if(infoContainer.getVisibility() == View.VISIBLE) return;

        errText.setText(errMessage);

        progressBar.setVisibility(View.GONE);
        infoContainer.setVisibility(View.GONE);
        errText.setVisibility(View.VISIBLE);
    }

}
