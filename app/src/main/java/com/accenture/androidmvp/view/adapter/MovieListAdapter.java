package com.accenture.androidmvp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accenture.androidmvp.R;
import com.accenture.androidmvp.model.pojo.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private OnMovieClickListener clickListener;
    private List<Movie> movieList;

    public MovieListAdapter(OnMovieClickListener clickListener) {
        this.clickListener = clickListener;
        this.movieList = new ArrayList<>();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final Movie movie = movieList.get(position);

        holder.title.setText(movie.title);
        holder.year.setText(movie.year);

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onMovieClicked(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void updateItems(List<Movie> movieList) {
        this.movieList.clear();
        this.movieList.addAll(movieList);
        this.notifyDataSetChanged();
    }


    static class MovieViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView title, year;

        public MovieViewHolder(View view) {
            super(view);
            this.rootView = view;
            title = (TextView) view.findViewById(R.id.title);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public interface OnMovieClickListener {
        void onMovieClicked(Movie movie);
    }

}