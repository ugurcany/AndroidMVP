package com.accenture.androidmvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.accenture.androidmvp.App;
import com.accenture.androidmvp.R;
import com.accenture.androidmvp.model.pojo.Movie;
import com.accenture.androidmvp.presenter.MovieListPresenter;
import com.accenture.androidmvp.view.adapter.MovieListAdapter;
import com.accenture.androidmvp.view.contract.IMovieListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class MovieListActivity extends AppCompatActivity implements IMovieListView, MovieListAdapter.OnMovieClickListener {

    @Inject
    MovieListPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.errText)
    TextView errText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movielist);

        App.injector().presenterComponent().inject(this);
        ButterKnife.bind(this);

        initRecyclerView();

        presenter.getMovieList(this, "lord");
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MovieListAdapter(this));
    }

    @Override
    public void onMovieClicked(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("imdbId", movie.imdbId);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        errText.setVisibility(View.GONE);
    }

    @Override
    public void showSuccess(List<Movie> movieList) {
        ((MovieListAdapter)recyclerView.getAdapter()).clearItems();
        ((MovieListAdapter)recyclerView.getAdapter()).addItems(movieList);

        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        errText.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errMessage) {
        errText.setText(errMessage);

        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        errText.setVisibility(View.VISIBLE);
    }

}
