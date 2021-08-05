package com.example.moviesapplication.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.moviesapplication.R;
import com.example.moviesapplication.databinding.PopularMovieBinding;
import com.example.moviesapplication.network.Constant;
import com.example.moviesapplication.pojo.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<MovieModel> movieList = new ArrayList<>();
    private Context mContext;
    private OnMovieClickListener listener;

    public  MovieAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void onSetClickListner(OnMovieClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         PopularMovieBinding binding;
        binding =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.popular_movie, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.binding.popularMovieName.setText(movieList.get(position).getTitle());
        holder.binding.popularMovieRate.setRating(movieList.get(position).getVote_average()/2);
        Glide.with(mContext)
                .load(Constant.poster +movieList.get(position).getBackdrop_path())
                .into(holder.binding.popularMoviePoster);

    }


    @Override
    public int getItemCount() {

        if(movieList!=null){
            return movieList.size();}
        return 0;
    }
    public void setList(List<MovieModel> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }
    public MovieModel getMoviePosition(int position) {
        return movieList.get(position);
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private PopularMovieBinding binding;

        public MovieViewHolder(@NonNull PopularMovieBinding binding) {
            super(binding.getRoot());
          this.binding = binding;
          binding.getRoot().setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if(listener!=null)
                listener.onMovieClick(view, getAdapterPosition());
        }
        }



 public interface OnMovieClickListener {
     void onMovieClick(View view, int pos);
 }
}
