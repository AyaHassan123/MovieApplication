package com.example.moviesapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviesapplication.pojo.FavouriteMovie;

@Database(entities = FavouriteMovie.class, version = 5, exportSchema = false)

public abstract class MovieDB extends RoomDatabase {

    private static MovieDB instance ;
    public  abstract MovieDao movieDao();

    public static synchronized MovieDB getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDB.class, "MovieDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
