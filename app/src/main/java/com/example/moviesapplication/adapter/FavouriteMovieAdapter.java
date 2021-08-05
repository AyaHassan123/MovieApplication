package com.example.moviesapplication.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapplication.R;
import com.example.moviesapplication.databinding.FragmentFavouriteBinding;
import com.example.moviesapplication.databinding.PopularMovieBinding;
import com.example.moviesapplication.network.Constant;
import com.example.moviesapplication.pojo.FavouriteMovie;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.ArrayList;
import java.util.List;

public class FavouriteMovieAdapter extends RecyclerView.Adapter<FavouriteMovieAdapter.FavouriteMovieViewHolder> {
    private List<FavouriteMovie> movie = new ArrayList<>();
    private Context mContext;

    public FavouriteMovieAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FavouriteMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PopularMovieBinding binding;
        binding =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.popular_movie, parent, false);
        return new FavouriteMovieAdapter.FavouriteMovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteMovieViewHolder holder, int position) {
        holder.binding.popularMovieName.setText(movie.get(position).getTitle());
        Glide.with(mContext)
                .load(Constant.poster +movie.get(position).getPoster_path())
                .into(holder.binding.popularMoviePoster);
        holder.binding.popularMovieRate.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        if(movie!=null){
            return movie.size();
        }
        return 0;
    }

    public void setList(List<FavouriteMovie> movie) {
        this.movie = movie;
        notifyDataSetChanged();
    }

    public FavouriteMovie getMoviePosition(int position) {
        return movie.get(position);
    }

    public class FavouriteMovieViewHolder extends RecyclerView.ViewHolder {
        private PopularMovieBinding binding;
        public FavouriteMovieViewHolder(@NonNull PopularMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
