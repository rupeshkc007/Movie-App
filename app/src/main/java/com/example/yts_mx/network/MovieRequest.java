package com.example.yts_mx.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.yts_mx.models.MovieModel;
import com.example.yts_mx.singleton.AppController;
import com.example.yts_mx.utils.AppUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieRequest {
    private OnFinished onFinished;

    public interface OnFinished {
        void onFinished(List<MovieModel> movieModelList);
    }

    public MovieRequest(OnFinished onFinished) {
        this.onFinished = onFinished;

    }

    public List<MovieModel> getMovies(String movieType, String page) {

        List<MovieModel> movieModelList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, movieType + AppUrl.MOVIE_LIST_PAGE + page, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.v("GETDATARESPONSE", "" + response);
                    JSONObject object = new JSONObject(response);
                    JSONObject data = object.getJSONObject("data");
                    JSONArray movies = data.getJSONArray("movies");
                    for (int i = 0; i < movies.length(); i++) {
                        JSONObject movieObject = movies.getJSONObject(i);

                        MovieModel movieModel = new MovieModel();
                        movieModel.id = movieObject.getString("id");
                        movieModel.url = movieObject.getString("url");
                        movieModel.imdbCode = movieObject.getString("imdb_code");
                        movieModel.title = movieObject.getString("title");
                        movieModel.titleEnglish = movieObject.getString("title_english");
                        movieModel.titleLong = movieObject.getString("title_long");
                        movieModel.slug = movieObject.getString("slug");
                        movieModel.year = movieObject.getString("year");
                        movieModel.rating = movieObject.getString("rating");
                        movieModel.runtime = movieObject.getString("runtime");
                        movieModel.genres = movieObject.getString("genres").replaceAll("[\\\"\\[\\]]", "");

                        movieModel.descriptionFull = movieObject.getString("description_full");
                        movieModel.ytTrailerCode = movieObject.getString("yt_trailer_code");
                        movieModel.language = movieObject.getString("language");
                        movieModel.mpaRating = movieObject.getString("mpa_rating");
                        movieModel.mediumCoverImage = movieObject.getString("medium_cover_image");
                        movieModel.backgroundImage = movieObject.getString("background_image");


                        movieModelList.add(movieModel);


                    }
                    onFinished.onFinished(movieModelList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });


        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest);

        return movieModelList;
    }


}
