package com.example.moviesapplication.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MoviesResponse {


    @SerializedName("results")
    @Expose
    private List<MovieModel> results;

    public List<MovieModel> getResults() {
        return results;
    }
    public void setResults(List<MovieModel> results) {
        this.results = results;
    }

}
