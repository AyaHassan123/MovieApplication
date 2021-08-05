package com.example.moviesapplication.ui.favourite;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapplication.R;
import com.example.moviesapplication.adapter.FavouriteMovieAdapter;
import com.example.moviesapplication.adapter.FragmentAdapter;
import com.example.moviesapplication.adapter.MovieAdapter;
import com.example.moviesapplication.databinding.FragmentFavouriteBinding;
import com.example.moviesapplication.pojo.FavouriteMovie;
import com.example.moviesapplication.viewModel.MovieViewModel;

import java.util.Collections;
import java.util.List;

public class FavouriteFragment  extends Fragment {

    private FragmentFavouriteBinding binding;
    private MovieViewModel movieViewModel;
    private FavouriteMovieAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_favourite, container, false);
        View view = binding.getRoot();

        adapter = new FavouriteMovieAdapter(getContext());
        binding.favMovieRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.favMovieRecyclerView.setLayoutManager(linearLayoutManager);
        binding.favMovieRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.favMovieRecyclerView.hasFixedSize();
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        viewFavMovie();
        return view;
    }

    private void viewFavMovie() {

        movieViewModel.movieListFav.observe(getActivity(), new Observer<List<FavouriteMovie>>() {
            @Override
            public void onChanged(List<FavouriteMovie> favouriteMovies) {
                if(favouriteMovies.size()!=0){
                    adapter.setList(favouriteMovies);
                   adapter.notifyDataSetChanged();
                }
                else{
                    adapter.setList(favouriteMovies);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
