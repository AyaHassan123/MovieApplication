package com.example.moviesapplication.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moviesapplication.pojo.FavouriteMovie;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface MovieDao  {
    @Insert
    Completable insertFav(FavouriteMovie movie);

    @Delete
    void deleteFavMovie(FavouriteMovie movie);

    @Query("select * from fav_table")
    Single<List<FavouriteMovie>> getFavMovies();
}
