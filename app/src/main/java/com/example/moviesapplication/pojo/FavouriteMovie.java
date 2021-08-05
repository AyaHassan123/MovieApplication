package com.example.moviesapplication.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fav_table")

public class FavouriteMovie {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name ="id")
    private int id;
    @ColumnInfo(name ="title")
    private String title;
    @ColumnInfo(name ="poster_path")
    private String poster_path;


    public FavouriteMovie(int id, String title, String poster_path) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
