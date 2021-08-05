package com.example.moviesapplication.ui.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.moviesapplication.R;
import com.example.moviesapplication.adapter.MovieAdapter;
import com.example.moviesapplication.databinding.FragmentHomeBinding;
import com.example.moviesapplication.pojo.MovieModel;
import com.example.moviesapplication.ui.movieDetails.DetailActivity;
import com.example.moviesapplication.viewModel.MovieViewModel;

import java.util.List;

public class HomeFragment extends Fragment implements MovieAdapter.OnMovieClickListener{
    private FragmentHomeBinding binding;
    private MovieViewModel movieViewModel;
    private MovieAdapter adapter;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");
                movieViewModel =
                ViewModelProviders.of(this).get(MovieViewModel.class);

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false);
        View view = binding.getRoot();

        adapter = new MovieAdapter(getContext());
        binding.recyclePopularMovies.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclePopularMovies.setLayoutManager(linearLayoutManager);
        binding.recyclePopularMovies.setItemAnimator(new DefaultItemAnimator());
        binding.recyclePopularMovies.hasFixedSize();
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        adapter.onSetClickListner((MovieAdapter.OnMovieClickListener) this);
        viewPopularMovie();

        // search movie
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMovie(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return view;
    }

    private void viewPopularMovie() {
        movieViewModel.getPopularMovie();
        movieViewModel.movieListPopular.observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels.size() !=0){
                    adapter.setList(movieModels);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Log.e("error", movieViewModel.error.toString());
                }
            }
        });
    }
    private void searchMovie(String query){
        movieViewModel.getSearchMovie(query);
        movieViewModel.movieListPopular.observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels.size() !=0){
                    adapter.setList(movieModels);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Log.e("error", movieViewModel.error.toString());
                }
            }
        });

    }
    // click on movie
    @Override
    public void onMovieClick(View view, int pos) {

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        MovieModel movieModel = adapter.getMoviePosition(pos);
        intent.putExtra("Movie",  movieModel);
        startActivity(intent);
    }
}
