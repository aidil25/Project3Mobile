package com.mobile.project3mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.project3mobile.R;
import com.mobile.project3mobile.model.MovieData;
import com.mobile.project3mobile.remote.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private List<MovieData> movieData;
    private Context context;
    private OnMovieItemSelectedListener  onMovieItemSelectedListener;


    public MovieListAdapter(Context context) {
        this.context = context;
        movieData = new ArrayList<>();
    }

    private void add(MovieData item){
        movieData.add(item);
        notifyItemInserted(movieData.size());
    }

    public void addAll(List<MovieData>movieDatas){
        for (MovieData movieData : movieDatas){
            add(movieData);
        }
    }

    public void remove(MovieData item){
        int position = movieData.indexOf(item);
        if(position >-1){
            movieData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear(int i){
        while (getItemCount()>0){
            remove(getItem(0));
        }
    }

    public MovieData getItem(int i){

        return movieData.get(i);
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_movie, viewGroup, false);
        final MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = movieViewHolder.getAdapterPosition();
                if(adapterPos != RecyclerView.NO_POSITION){
                    OnMovieItemSelectedListener onMovieItemSelectedListener = null;
                    if (onMovieItemSelectedListener != null){
                        onMovieItemSelectedListener.onItemClick(movieViewHolder.itemView,adapterPos);
                    }
                }
            }
        });
        return movieViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final MovieData movieData1 = movieData.get(position);
        holder.bind(movieData1);
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMovie;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMovie = (ImageView)itemView.findViewById(R.id.img_data);

        }

        public void bind(MovieData movieData1) {
            Picasso.with(context).load(Constant.IMG_URL+movieData1.getPosterPath()).into(imgMovie);
        }
    }

    public  void setOnMovieItemSelectedListener(OnMovieItemSelectedListener onMovieItemSelectedListener){
        this.onMovieItemSelectedListener = onMovieItemSelectedListener;

    }

    private interface OnMovieItemSelectedListener {

        void onItemClick(View itemView, int adapterPos);
    }


   
}
