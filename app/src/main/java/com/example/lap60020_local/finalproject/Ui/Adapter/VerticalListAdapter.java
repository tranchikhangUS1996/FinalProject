package com.example.lap60020_local.finalproject.Ui.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap60020_local.finalproject.GlideApp;
import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MyApiClient;
import com.example.lap60020_local.finalproject.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerticalListAdapter extends RecyclerView.Adapter {

    private List<Movie> Movies;
    private Context context;
    private int TypeOfList = 0;
    private LoadMoreNotifier loadMoreNotifier;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    int addition = 1;

    public VerticalListAdapter(Context context, LoadMoreNotifier notifier, RecyclerView recyclerView) {
        this.context = context;
        this.loadMoreNotifier = notifier;
        this.recyclerView = recyclerView;
        Movies = new ArrayList<>();
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastseen = 0;
                switch (TypeOfList) {
                    case 0:
                        lastseen = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                        break;
                    case 1:
                        lastseen = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                        break;
                }
                    loadMoreNotifier.onScroll(lastseen);
                    if(lastseen  == Movies.size()) {
                        loadMoreNotifier.loadMore();
                    }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public void receiveData(List<Movie> data, int seePosition) {
        Movies = data;
        layoutManager.scrollToPosition(seePosition);
    }

    public void addLoading() {
        addition = 1;
        notifyDataSetChanged();
    }

    public void removeLoading() {
        addition = 0;
        notifyDataSetChanged();
    }

    public void setType(int type) {
        this.TypeOfList = type;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = null;
        switch (viewType) {
            case -1:
                return new LoadingViewholder(LayoutInflater.from(context).inflate(R.layout.loading_layout, parent, false));
            case 0:
                v = (CardView) LayoutInflater.from(context).inflate(R.layout.layout_detail_list_item, parent, false);
                return new MyViewHolder(v);
            case 1:
                v = (CardView) LayoutInflater.from(context).inflate(R.layout.layout_singgle_list_item, parent, false);
                return new MySingleViewholder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof LoadingViewholder) return;

        Movie movie = Movies.get(position);
        ImageView imageView = null;
        if(holder instanceof MySingleViewholder) {
            MySingleViewholder singleViewholder = (MySingleViewholder) holder;
            singleViewholder.date.setText(movie.getReleaseDate());
            singleViewholder.cardView.setOnClickListener(new onCardClick(movie.getId()));
            if(movie.isWatchlist()) {
                singleViewholder.watchlist.setImageResource(R.drawable.added);
            }
            else {
                singleViewholder.watchlist.setImageResource(R.drawable.unadd);
            }

            singleViewholder.watchlist.setOnClickListener(new watchListListener(movie));

            imageView = singleViewholder.poster;
        }
        else if(holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.tittle.setText(movie.getTitle());
            myViewHolder.rate.setText(String.valueOf(movie.getVoteAverage()));
            myViewHolder.releaseDate.setText(movie.getReleaseDate());
            myViewHolder.review.setText(movie.getOverview());
            myViewHolder.cardView.setOnClickListener(new onCardClick(movie.getId()));
            if(movie.isWatchlist()) {
                myViewHolder.WatchlistImage.setImageResource(R.drawable.added);
            }
            else {
                myViewHolder.WatchlistImage.setImageResource(R.drawable.unadd);
            }

            myViewHolder.WatchlistImage.setOnClickListener(new watchListListener(movie));

            imageView = myViewHolder.poster;
        }

        String path = MyApiClient.IMAGE_PATH + movie.getPosterPath();
        GlideApp.with(context)
                .load(Uri.parse(path))
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(imageView);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == Movies.size()) {
            return -1;
        }
        return this.TypeOfList;
    }

    @Override
    public int getItemCount() {
        return Movies.size() + addition;
    }

    class LoadingViewholder extends RecyclerView.ViewHolder{

        public LoadingViewholder(View itemView) {
            super(itemView);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @Nullable
        @BindView(R.id.detail_list_item_poster)
        ImageView poster;
        @Nullable
        @BindView(R.id.detail_list_item_add_to_watchlist)
        ImageView WatchlistImage;
        @Nullable
        @BindView(R.id.detail_list_item_title)
        TextView tittle;
        @Nullable
        @BindView(R.id.detail_list_item_release_date)
        TextView releaseDate;
        @Nullable
        @BindView(R.id.detail_list_item_review)
        TextView review;
        @Nullable
        @BindView(R.id.detail_list_item_rate)
        TextView rate;
        CardView cardView;
        public MyViewHolder(CardView itemView) {
            super(itemView);
            this.cardView = itemView;
            ButterKnife.bind(this,itemView);
        }
    }

    class MySingleViewholder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.single_list_image)
        ImageView poster;
        @Nullable
        @BindView(R.id.single_add_to_watchlist)
        ImageView watchlist;
        @Nullable
        @BindView(R.id.single_list_release_date)
        TextView date;
        CardView cardView;
        public MySingleViewholder(CardView itemView) {
            super(itemView);
            cardView = itemView;
            ButterKnife.bind(this,itemView);
        }
    }
}
