package com.sachin.hooq.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sachin.hooq.Model.MovieResponseModel;
import com.sachin.hooq.R;

import java.util.ArrayList;

public class MovieRecListAdapter extends RecyclerView.Adapter<MovieRecListAdapter.MyViewHolder> {

    private final ArrayList<MovieResponseModel> movielist;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private RelativeLayout parent_rel;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_M);
            textView = (TextView) itemView.findViewById(R.id.tv_M);
            parent_rel = (RelativeLayout) itemView.findViewById(R.id.parent_rel);
        }
    }

    public MovieRecListAdapter(Context context, ArrayList<MovieResponseModel> movieResponseModels) {
        this.context = context;
        this.movielist = movieResponseModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movielist_single_rec_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        holder.textView.setText(movielist.get(listPosition).title);
        String imageURL = "https://image.tmdb.org/t/p/w500/" + movielist.get(listPosition).poster_path;
        Glide.with(context).load(imageURL).into(holder.imageView);
        holder.parent_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if (movielist != null && movielist.size() > 0) {
            return movielist.size();
        } else {
            return 0;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}