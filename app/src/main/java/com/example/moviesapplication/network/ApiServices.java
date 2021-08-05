package com.example.moviesapplication.network;

import com.example.moviesapplication.pojo.MovieTrailerResponse;
import com.example.moviesapplication.pojo.MoviesResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("3/movie/popular")
    Observable<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey,
                                                 @Query("page") String page);

    @GET("3/movie/{movie_id}/similar")
    Observable<MoviesResponse> getTopRatedrMovies(@Path("movie_id") String movieId,
                                                @Query("api_key") String apiKey,
                                                 @Query("page") String page);

    @GET("3/search/movie")
    Observable<MoviesResponse> searchMovie(
            @Query("api_key")String key ,
            @Query("query") String query,
            @Query("page") String page
    );


    @GET("3/movie/{movie_id}/videos")
    Observable<MovieTrailerResponse> getVideos(@Path("movie_id") String id, @Query("api_key") String key);

}
