package com.example.yts_mx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.yts_mx.models.MovieModel;
import com.example.yts_mx.utils.AppUrl;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MovieDetails extends YouTubeBaseActivity {
    private YouTubePlayerView youTubePlayerView;
    private ImageView ivMovieDetailBg, ivPosterDetail;
    private TextView tvTitleDetail, tvLanguageDetail, tvRuntimeDetail, tvGenresDetail, tvRatingDetail, tvTrailerDetail, tvOverviewDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        youTubePlayerView = findViewById(R.id.ytPlayer);
        ivMovieDetailBg = findViewById(R.id.iv_movie_detail_bg);
        ivPosterDetail = findViewById(R.id.iv_poster_detail);
        tvTitleDetail = findViewById(R.id.tv_title_detail);
        tvLanguageDetail = findViewById(R.id.tv_language_detail);
        tvGenresDetail = findViewById(R.id.tv_genres_detail);
        tvRatingDetail = findViewById(R.id.tv_rating_detail);
        tvTrailerDetail = findViewById(R.id.tv_trailer_detail);
        tvOverviewDetail = findViewById(R.id.tv_overview_detail);
        tvRuntimeDetail = findViewById(R.id.tv_runtime_detail);


        getDataFromIntent();
    }

    private void getDataFromIntent() {

        if (getIntent().hasExtra("movie")) {
            MovieModel movieModel = getIntent().getParcelableExtra("movie");


            tvTitleDetail.setText(movieModel.titleLong);
            tvLanguageDetail.setText(movieModel.language);
            tvGenresDetail.setText(movieModel.genres);
            tvRatingDetail.setText(movieModel.rating + "/10");
            tvOverviewDetail.setText(movieModel.descriptionFull);
            Glide.with(this).load(movieModel.mediumCoverImage).into(ivPosterDetail);
            Glide.with(this).load(movieModel.backgroundImage).into(ivMovieDetailBg);


            int hr = Integer.valueOf(movieModel.runtime) / 60;
            int min = Integer.valueOf(movieModel.runtime) % 60;

            tvRuntimeDetail.setText(String.valueOf(hr) + " hr " + String.valueOf(min) + " min");

            if (movieModel.ytTrailerCode.length() == 0) {
                tvTrailerDetail.setVisibility(View.GONE);
            }

            tvTrailerDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    youTubePlayerView.setVisibility(View.VISIBLE);
                    youTubePlayerView.initialize(AppUrl.API_KEY, new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            youTubePlayer.loadVideo(movieModel.ytTrailerCode);
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });
                }
            });


        }

    }
}