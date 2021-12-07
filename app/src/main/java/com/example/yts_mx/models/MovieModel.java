package com.example.yts_mx.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {

    public String id;
    public String url;
    public String imdbCode;
    public String title;
    public String titleEnglish;
    public String titleLong;
    public String slug;
    public String year;
    public String rating;
    public String runtime;
    public String genres;
    public String downloadCount;
    public String likeCount;
    public String summary;
    public String descriptionFull;
    public String synopsis;
    public String ytTrailerCode;
    public String language;
    public String mpaRating;
    public String backgroundImage;
    public String backgroundImageOriginal;
    public String smallCoverImage;
    public String mediumCoverImage;
    public String largeCoverImage;
    public String state;
    public String dateUploaded;
    public String dateUploadedUnix;


    public MovieModel() {

    }

    public MovieModel(Parcel in) {
        id = in.readString();
        url = in.readString();
        imdbCode = in.readString();
        title = in.readString();
        titleEnglish = in.readString();
        titleLong = in.readString();
        slug = in.readString();
        year = in.readString();
        rating = in.readString();
        runtime = in.readString();
        genres = in.readString();
        downloadCount = in.readString();
        likeCount = in.readString();
        summary = in.readString();
        descriptionFull = in.readString();
        synopsis = in.readString();
        ytTrailerCode = in.readString();
        language = in.readString();
        mpaRating = in.readString();
        backgroundImage = in.readString();
        backgroundImageOriginal = in.readString();
        smallCoverImage = in.readString();
        mediumCoverImage = in.readString();
        largeCoverImage = in.readString();
        state = in.readString();
        dateUploaded = in.readString();
        dateUploadedUnix = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeString(id);
        dest.writeString(url);
        dest.writeString(imdbCode);
        dest.writeString(title);
        dest.writeString(titleEnglish);
        dest.writeString(titleLong);
        dest.writeString(slug);
        dest.writeString(year);
        dest.writeString(rating);
        dest.writeString(runtime);
        dest.writeString(genres);
        dest.writeString(downloadCount);
        dest.writeString(likeCount);
        dest.writeString(summary);
        dest.writeString(descriptionFull);
        dest.writeString(synopsis);
        dest.writeString(ytTrailerCode);
        dest.writeString(language);
        dest.writeString(mpaRating);
        dest.writeString(backgroundImage);
        dest.writeString(backgroundImageOriginal);
        dest.writeString(smallCoverImage);
        dest.writeString(mediumCoverImage);
        dest.writeString(largeCoverImage);
        dest.writeString(state);
        dest.writeString(dateUploaded);
        dest.writeString(dateUploadedUnix);
    }
}
