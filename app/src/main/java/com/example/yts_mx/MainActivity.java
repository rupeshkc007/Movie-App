package com.example.yts_mx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yts_mx.adapter.MovieListAdapter;
import com.example.yts_mx.models.MovieModel;
import com.example.yts_mx.network.MovieRequest;
import com.example.yts_mx.utils.AppUrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements MovieRequest.OnFinished, MovieListAdapter.OnMovieClick {

    private RecyclerView rv_movieList;
    int page = 1;
    private LinearLayoutManager linearLayoutManager;
    private List<MovieModel> movieModelList = new ArrayList<>();
    private MovieListAdapter movieListAdapter = null;
    private Toolbar main_toolbar;
    private String movieSorting = AppUrl.TOP_MOVIES_LIST;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_movieList = findViewById(R.id.rv_movieList);
        main_toolbar = findViewById(R.id.main_toolbar);
        progressBar = findViewById(R.id.progressBar);

        setSupportActionBar(main_toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Top Movies");

        linearLayoutManager = new LinearLayoutManager(this);
        rv_movieList.setLayoutManager(linearLayoutManager);
        rv_movieList.setHasFixedSize(true);


        new MovieRequest(this).getMovies(movieSorting, String.valueOf(page));


        rv_movieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!rv_movieList.canScrollVertically(1)) {

                    progressBar.setVisibility(View.VISIBLE);

                    page = page + 1;
                    if (movieModelList.size() > 0)
                        new MovieRequest(MainActivity.this).getMovies(movieSorting, String.valueOf(page));

                    Log.v("SCROLL", "" + page);

                }

            }
        });

    }

    @Override
    public void onFinished(List<MovieModel> movieModelList) {
        if (rv_movieList.getVisibility() == View.GONE) {
            rv_movieList.setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < movieModelList.size(); i++) {
            Log.v("MOVIETITLE", movieModelList.get(i).title);
        }
        progressBar.setVisibility(View.GONE);

        if (page == 1) {

            this.movieModelList = movieModelList;
            movieListAdapter = new MovieListAdapter(movieModelList, this, this);
            rv_movieList.setAdapter(movieListAdapter);
        } else {

            this.movieModelList.addAll(movieModelList);
            movieListAdapter.notifyDataChange(this.movieModelList);
        }


    }


    @Override
    public void onMovieClick(MovieModel movieModel) {
        startActivity(new Intent(this, MovieDetails.class)
                .putExtra("movie", movieModel));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sorting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_title:
                getMoviesFromMenu("Movies", AppUrl.TITLE_MOVIES_LIST);
                return true;
            case R.id.menu_year:
                getMoviesFromMenu("Latest Movies", AppUrl.YEAR_MOVIES_LIST);
                return true;
            case R.id.menu_rating:
                getMoviesFromMenu("Top Rated", AppUrl.TOP_MOVIES_LIST);
                return true;
            case R.id.menu_date_added:
                getMoviesFromMenu("Recently Added", AppUrl.DATE_ADDED_MOVIES_LIST);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getMoviesFromMenu(String toolbarTitle, String sortName) {
        rv_movieList.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        movieModelList.clear();
        getSupportActionBar().setTitle(toolbarTitle);
        movieSorting = sortName;
        page = 1;
        new MovieRequest(this).getMovies(movieSorting, String.valueOf(page));

    }
}