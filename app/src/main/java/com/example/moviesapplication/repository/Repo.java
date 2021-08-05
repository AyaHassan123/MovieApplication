package com.example.moviesapplication.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.moviesapplication.db.MovieDB;
import com.example.moviesapplication.network.Constant;
import com.example.moviesapplication.network.RetrofitModule;
import com.example.moviesapplication.pojo.FavouriteMovie;
import com.example.moviesapplication.pojo.MovieModel;
import com.example.moviesapplication.pojo.MovieTrailerModel;
import com.example.moviesapplication.pojo.MovieTrailerResponse;
import com.example.moviesapplication.pojo.MoviesResponse;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repo {

    public MutableLiveData<List<MovieModel>> movieListPopular = new MutableLiveData<>();
    public MutableLiveData<List<MovieModel>> movieListSimilar = new MutableLiveData<>();
    public MutableLiveData<List<MovieTrailerModel>> movieListVideos= new MutableLiveData<>();
    public MutableLiveData<List<FavouriteMovie>> movieListFav= new MutableLiveData<>();


    public MutableLiveData<String > error = new MutableLiveData<>();
    public static Repo repo;

    public static synchronized Repo getInstance(){
        if(repo == null){
            repo = new Repo();
        }
            return repo;
    }

    public void getPopularMovie(){
        RetrofitModule.getService()
                .getPopularMovies(Constant.apiKey,"1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        
                    }

                    @Override
                    public void onNext(@NonNull MoviesResponse moviesResponse) {

                        Repo.getInstance().movieListPopular.setValue(moviesResponse.getResults());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Repo.getInstance().error.setValue(e.getLocalizedMessage());
                        Log.e("list", error.getValue());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getSimilarMovie(String id) {
        RetrofitModule.getService()
                .getTopRatedrMovies(id, Constant.apiKey,"1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MoviesResponse moviesResponse) {

                        Repo.getInstance().movieListSimilar.setValue(moviesResponse.getResults());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Repo.getInstance().error.setValue(e.getLocalizedMessage());
                        Log.e("list", error.getValue());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getSearchMovie(String query){
        RetrofitModule.getService()
                .searchMovie(Constant.apiKey,query,"1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MoviesResponse moviesResponse) {

                        Repo.getInstance().movieListPopular.setValue(moviesResponse.getResults());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Repo.getInstance().error.setValue(e.getLocalizedMessage());
                        Log.e("list", error.getValue());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getVideoMovies(String id){
        RetrofitModule.getService()
                .getVideos(id,Constant.apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieTrailerResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MovieTrailerResponse movieTralierResponse) {

                        Repo.getInstance().movieListVideos.setValue(movieTralierResponse.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Repo.getInstance().error.setValue(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void insertFavMovie(FavouriteMovie movie , Context context) {
            MovieDB.getInstance(context)
            .movieDao()
             .insertFav(movie)
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
             .subscribe(new CompletableObserver() {
                 @Override
                 public void onSubscribe(@io.reactivex.annotations.NonNull io.reactivex.disposables.Disposable d) {

                 }

                 @Override
                 public void onComplete() {

                     Log.e("success","true");
                 }

                 @Override
                 public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                     Log.e("failed",e.getLocalizedMessage());

                 }
             });
    }
    public void deleteFavMovie(FavouriteMovie movie, Context context){
        MovieDB.getInstance(context)
                .movieDao()
        .deleteFavMovie(movie);
    }
    public void getFavMovie(Context context) {
        MovieDB.getInstance(context)
                .movieDao()
                .getFavMovies()
               .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FavouriteMovie>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull io.reactivex.disposables.Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<FavouriteMovie> favouriteMovies) {

                        Repo.getInstance().movieListFav.setValue(favouriteMovies);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });
    }
}
