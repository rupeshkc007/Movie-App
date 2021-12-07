package com.example.yts_mx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yts_mx.R;
import com.example.yts_mx.models.MovieModel;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
    private List<MovieModel> movieModelList;
    private Context context;
    private OnMovieClick onMovieClick;

    public interface OnMovieClick {
        void onMovieClick(MovieModel movieModel);

    }


    public MovieListAdapter(List<MovieModel> movieModelList, Context context, OnMovieClick onMovieClick) {
        this.movieModelList = movieModelList;
        this.context = context;
        this.onMovieClick = onMovieClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {
        MovieModel movieModel = movieModelList.get(position);

        holder.tvTitle.setText(movieModel.title);
        holder.tvYear.setText(movieModel.year);
        holder.tvRating.setText(movieModel.rating + "/10");
        holder.tvGenres.setText(movieModel.genres);
        Glide.with(context).load(movieModel.mediumCoverImage).into(holder.ivPoster);
        Glide.with(context).load(movieModel.backgroundImage).into(holder.ivBackground);
        holder.cvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMovieClick.onMovieClick(movieModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void notifyDataChange(List<MovieModel> movieModelList1) {
        this.movieModelList = movieModelList1;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvYear, tvGenres, tvRating;
        ImageView ivPoster, ivBackground;
        CardView cvMovie;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvYear = itemView.findViewById(R.id.tv_year);
            tvGenres = itemView.findViewById(R.id.tv_genres);
            tvRating = itemView.findViewById(R.id.tv_rating);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            ivBackground = itemView.findViewById(R.id.iv_cover_image);
            cvMovie = itemView.findViewById(R.id.cv_movie);


        }

    }

}
