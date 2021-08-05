package com.example.moviesapplication.ui.movieDetails;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.moviesapplication.R;
import com.example.moviesapplication.adapter.MovieAdapter;
import com.example.moviesapplication.databinding.ActivityDetailBinding;
import com.example.moviesapplication.network.Constant;
import com.example.moviesapplication.pojo.FavouriteMovie;
import com.example.moviesapplication.pojo.MovieModel;
import com.example.moviesapplication.pojo.MovieTrailerModel;
import com.example.moviesapplication.viewModel.MovieViewModel;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {

    private ActivityDetailBinding binding;
    private MovieViewModel movieViewModel;
    private MovieAdapter adapter;
   private MovieModel movieModel;
   int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        binding = DataBindingUtil.setContentView(DetailActivity.this,R.layout.activity_detail);
        movieViewModel =  ViewModelProviders.of(this).get(MovieViewModel.class);


        adapter = new MovieAdapter(getApplicationContext());
        binding.recyclerSimilarMovies.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerSimilarMovies.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        if(getIntent() != null){
             movieModel= (MovieModel)getIntent().getSerializableExtra("Movie");
            //getSupportActionBar().setTitle("Movie Details");
            String imageUrl = Constant.poster + movieModel.getBackdrop_path();
            Glide.with(getApplicationContext())
                    .load(imageUrl)
                    .into(binding.detailedMoviePoster);
            binding.movieDetailedName.setText(movieModel.getTitle());
            binding.detailedMovieOverView.setText(movieModel.getOverview());
        }

        viewSimilarMovie(movieModel.getId());
        adapter.onSetClickListner((MovieAdapter.OnMovieClickListener) this);
        if(binding.movieTrailerButton.isClickable()){
            getMovieVideo();
        }
        viewStateFavButton();
        if(binding.favButton.isClickable()) {
            listnerFavMovie();
        }
        }

     private void listnerFavMovie(){
        binding.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count==0){
                    binding.favButton.setImageResource(R.drawable.ic_baseline_favorite_24);
                    insertFavMovie();
                    viewStateFavButton();
                }
                else{
                    binding.favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    deleteFavMovie();
                    viewStateFavButton();
                }
            }
        });

     }
    private void viewStateFavButton() {
        movieViewModel.getFavMovie(getApplicationContext());
        movieViewModel.movieListFav.observe(this, new Observer<List<FavouriteMovie>>() {
            @Override
            public void onChanged(List<FavouriteMovie> favouriteMovies) {
                Log.e("size", String.valueOf(favouriteMovies.size()));
                if(favouriteMovies.size()==0){
                    binding.favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    count = 0;
                }
                else{
                    for(int i=0;i<favouriteMovies.size();i++){
                        if(favouriteMovies.get(i).getId()==movieModel.getId()){
                            binding.favButton.setImageResource(R.drawable.ic_baseline_favorite_24);
                            count =1;
                            break;
                        }
                        else{
                            binding.favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                            count = 0;
                        }
                    }
                }
            }
        });
    }
    private void deleteFavMovie() {
        FavouriteMovie movie = new FavouriteMovie(movieModel.getId(),movieModel.getTitle(),movieModel.getBackdrop_path());
        movieViewModel.deletFavMovie(movie,getApplicationContext());
    }
    private void insertFavMovie() {
        FavouriteMovie movie = new FavouriteMovie(movieModel.getId(),movieModel.getTitle(),movieModel.getBackdrop_path());
        movieViewModel.insertFavMovie(movie,getApplicationContext());
    }

    private void getMovieVideo() {
        movieViewModel.getVideoMovies(String.valueOf(movieModel.getId()));
        movieViewModel.movieListVideos.observe(this, new Observer<List<MovieTrailerModel>>() {
            @Override
            public void onChanged(List<MovieTrailerModel> movieTrailerModels) {
                if(movieTrailerModels.size()!=0){
                  listnerMovieTrailer(movieTrailerModels.get(0).getKey());
                }
            }
        });
    }
    private void listnerMovieTrailer(String key) {
        binding.movieTrailerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MovieTrailerActivity.class);
                intent.putExtra("MovieKey",key);
               startActivity(intent);
            }
        });
    }
    private void viewSimilarMovie(int id){
        movieViewModel.getSimilarMovie(String.valueOf(id));
        movieViewModel.movieListSimilar.observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {

                if(movieModels.size() !=0) {
                    adapter.setList(movieModels);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onMovieClick(View view, int pos) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        MovieModel movieModel = adapter.getMoviePosition(pos);
        intent.putExtra("Movie",  movieModel);
        startActivity(intent);
    }
}
