package com.example.moviesapplication.ui.movieDetails;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.moviesapplication.R;
import com.example.moviesapplication.databinding.ActivityMovieTailerBinding;
import com.example.moviesapplication.network.Constant;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

public class MovieTrailerActivity  extends YouTubeBaseActivity {

    private ActivityMovieTailerBinding binding;
    private String key ;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_tailer);

        binding = DataBindingUtil.setContentView(MovieTrailerActivity.this,R.layout.activity_movie_tailer);
        if(getIntent()!=null){

          key =  getIntent().getStringExtra("MovieKey");

            viewMovieVideo(key);
        }
    }

    private void viewMovieVideo(String key) {
        binding.trailerPlayer.initialize(Constant.apiKeyTrailer, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(key);
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
