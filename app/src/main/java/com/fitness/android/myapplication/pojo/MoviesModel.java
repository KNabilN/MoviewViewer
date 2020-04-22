package com.fitness.android.myapplication.pojo;

public class MoviesModel {

   private String poster_path;
   private String overview;
   private String original_title;
   private String release_date;
   private Float popularity;

    public String getRelease_date() {
        return release_date;
    }

    public Float getPopularity() {
        return popularity;
    }

    public MoviesModel(Float popularity , String poster_path, String overview, String original_title, String release_date) {
        this.poster_path = poster_path;
        this.overview = overview;
        this.original_title = original_title;
        this.release_date = release_date;
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginal_title() {
        return original_title;
    }
}
