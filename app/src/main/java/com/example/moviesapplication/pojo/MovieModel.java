package com.example.moviesapplication.pojo;



import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class MovieModel implements Serializable {

    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("original_title")
    private String title;
    @SerializedName("vote_average")
    private Float vote_average;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private Float popularity;
    @SerializedName("releaseDate")
    private String releaseDate;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("id")
    private int id;

    public MovieModel(String poster_path, String title, Float vote_average, String overview,
                      Float popularity, String releaseDate, String backdrop_path, int id) {
        this.poster_path = poster_path;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.backdrop_path = backdrop_path;
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getVote_average() {
        return vote_average;
    }

    public void setVote_average(Float vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

