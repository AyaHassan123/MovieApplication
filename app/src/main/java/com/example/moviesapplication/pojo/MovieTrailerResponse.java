package com.example.moviesapplication.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieTrailerResponse {

    @SerializedName("results")
    @Expose
    private List<MovieTrailerModel> results;

    public List<MovieTrailerModel> getResults() {
        return results;
    }
    public void setResults(List<MovieTrailerModel> results) {
        this.results = results;
    }

}
