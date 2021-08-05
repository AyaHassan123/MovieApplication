package com.example.moviesapplication.viewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapplication.pojo.FavouriteMovie;
import com.example.moviesapplication.pojo.MovieModel;
import com.example.moviesapplication.pojo.MovieTrailerModel;
import com.example.moviesapplication.repository.Repo;

import java.util.List;

public class MovieViewModel extends ViewModel {

    public MutableLiveData<List<MovieModel>> movieListPopular = new MutableLiveData<>();
    public MutableLiveData<List<MovieModel>> movieListSimilar = new MutableLiveData<>();
    public MutableLiveData<List<MovieTrailerModel>> movieListVideos = new MutableLiveData<>();
    public MutableLiveData<List<FavouriteMovie>> movieListFav= new MutableLiveData<>();

    public MutableLiveData<String > error = new MutableLiveData<>();

    private Repo repo;

    public MovieViewModel() {
        repo = Repo.getInstance();
        movieListPopular = repo.movieListPopular;
        movieListSimilar = repo.movieListSimilar;
        movieListVideos = repo.movieListVideos;
        movieListFav = repo.movieListFav;

        error = repo.error;
    }

    public void getPopularMovie(){
        repo.getPopularMovie();
    }
    public void getSimilarMovie(String id){
        repo.getSimilarMovie(id);

    }
    public void getSearchMovie(String searchKey){
        repo.getSearchMovie(searchKey);}
    public void getVideoMovies(String id){
        repo.getVideoMovies(id);}
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
