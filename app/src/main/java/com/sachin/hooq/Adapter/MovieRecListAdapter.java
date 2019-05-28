package com.sachin.hooq.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sachin.hooq.Model.MovieResponseModel;
import com.sachin.hooq.R;

import java.util.ArrayList;

public class MovieRecListAdapter extends RecyclerView.Adapter {

    private final Context context;

    interface Callbacks {
        public void onClickLoadMore();
    }

    private Callbacks mCallbacks;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private boolean mWithHeader = false;
    private boolean mWithFooter = false;
    private ArrayList<MovieResponseModel> mFeedList;

    public MovieRecListAdapter(Context mc, ArrayList<MovieResponseModel> feedList) {
        this.mFeedList = feedList;
        this.context = mc;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;

        if (viewType == TYPE_FOOTER) {

            itemView = View.inflate(parent.getContext(), R.layout.item_loading, null);
            return new LoadMoreViewHolder(itemView);

        } else {

            itemView = View.inflate(parent.getContext(), R.layout.movielist_single_rec_item, null);
            return new ElementsViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof LoadMoreViewHolder) {

            LoadMoreViewHolder loadMoreViewHolder = (LoadMoreViewHolder) holder;
            ((LoadMoreViewHolder) holder).progressBar.setVisibility(View.VISIBLE);

            loadMoreViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCallbacks != null)
                        mCallbacks.onClickLoadMore();
                }
            });

        } else {
            ElementsViewHolder elementsViewHolder = (ElementsViewHolder) holder;
            if (position < mFeedList.size()) {
                elementsViewHolder.name.setText(mFeedList.get(position).title);
                String imageURL = "https://image.tmdb.org/t/p/w500/" + mFeedList.get(position).poster_path;
                Glide.with(context).load(imageURL).into(elementsViewHolder.icon);
                elementsViewHolder.parent_rel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        int itemCount = mFeedList.size() + 1;
        if (mWithHeader)
            itemCount++;
        if (mWithFooter)
            itemCount++;
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (mWithHeader && isPositionHeader(position))
            return TYPE_HEADER;
        if (mWithFooter && isPositionFooter(position))
            return TYPE_FOOTER;
        return TYPE_ITEM;
    }

    public boolean isPositionHeader(int position) {
        return position == 0 && mWithHeader;
    }

    public boolean isPositionFooter(int position) {
        return position == getItemCount() - 1 && mWithFooter;
    }

    public void setWithHeader(boolean value) {
        mWithHeader = value;
    }

    public void setWithFooter(boolean value) {
        mWithFooter = value;
    }

    public void setCallback(Callbacks callbacks) {
        mCallbacks = callbacks;
    }

    public class ElementsViewHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView name;
        private RelativeLayout parent_rel;

        public ElementsViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.img_M);
            name = (TextView) itemView.findViewById(R.id.tv_M);
            parent_rel = (RelativeLayout) itemView.findViewById(R.id.parent_rel);
        }
    }

    public class LoadMoreViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;


        public LoadMoreViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);

        }
    }
}