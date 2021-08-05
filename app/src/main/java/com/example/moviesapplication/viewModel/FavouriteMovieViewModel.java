package com.example.moviesapplication.viewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapplication.pojo.FavouriteMovie;
import com.example.moviesapplication.repository.Repo;

import java.util.List;

public class FavouriteMovieViewModel  extends ViewModel {

    public MutableLiveData<List<FavouriteMovie>> movieListFav= new MutableLiveData<>();

    private Repo repo;

    public FavouriteMovieViewModel() {
        repo = Repo.getInstance();
        movieListFav = repo.movieListFav;
    }

    public void insertFavMovie(FavouriteMovie movie, Context context){
        repo.insertFavMovie(movie,context);
    }
    public void deletFavMovie(FavouriteMovie movie, Context context){
        repo.deleteFavMovie(movie,context);
    }
    public void getFavMovie(Context context){
        repo.getFavMovie(context);
    }
}
