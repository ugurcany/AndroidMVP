package com.accenture.androidmvp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.accenture.androidmvp.App;
import com.accenture.androidmvp.R;
import com.accenture.androidmvp.model.pojo.Movie;
import com.accenture.androidmvp.presenter.MovieListPresenter;
import com.accenture.androidmvp.util.NavigationManager;
import com.accenture.androidmvp.util.ViewUtils;
import com.accenture.androidmvp.view.adapter.MovieListAdapter;
import com.accenture.androidmvp.view.contract.IMovieListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;

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

        App.injectorFactory().viewInjector().inject(this);
        ButterKnife.bind(this);

        presenter.setView(this);

        initRecyclerView();
    }

    @Override
    protected void onDestroy() {
        presenter.setView(null);
        super.onDestroy();
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MovieListAdapter(this));
    }

    @OnEditorAction(R.id.editText)
    public boolean onEditTextAction(TextView textView, int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            ViewUtils.hideKeyboard(this);
            presenter.getMovieList(textView.getText().toString().trim());
            return true;
        }
        return false;
    }

    @Override
    public void onMovieClicked(Movie movie) {
        NavigationManager.goToMovieDetail(this, movie.imdbId);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        errText.setVisibility(View.GONE);
    }

    @Override
    public void showSuccess(List<Movie> movieList) {
        ((MovieListAdapter) recyclerView.getAdapter()).updateItems(movieList);

        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        errText.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errMessage) {
        if(recyclerView.getVisibility() == View.VISIBLE) return;

        errText.setText(errMessage);

        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        errText.setVisibility(View.VISIBLE);
    }

}
