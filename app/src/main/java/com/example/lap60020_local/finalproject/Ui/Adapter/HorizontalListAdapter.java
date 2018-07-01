package com.example.lap60020_local.finalproject.Ui.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

public class HorizontalListAdapter extends RecyclerView.Adapter {

    List<Movie> Movies;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Context context;

    public HorizontalListAdapter( RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager, Context context) {
        Movies = new ArrayList<>();
        this.recyclerView = recyclerView;
        this.layoutManager = layoutManager;
        this.context = context;
    }

    void receiveData(List<Movie> data) {
        Movies = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(context).inflate(R.layout.layout_singgle_list_item, parent, false);
        return new MySingleViewholder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = Movies.get(position);
        VerticalListAdapter.MySingleViewholder singleViewholder = (VerticalListAdapter.MySingleViewholder) holder;
        singleViewholder.date.setText(movie.getReleaseDate());
        singleViewholder.cardView.setOnClickListener(new onCardClick(movie.getId()));
        if(movie.isWatchlist()) {
            singleViewholder.watchlist.setImageResource(R.drawable.added);
        }
        else {
            singleViewholder.watchlist.setImageResource(R.drawable.unadd);
        }

        singleViewholder.watchlist.setOnClickListener(new watchListListener(movie));

        String path = MyApiClient.IMAGE_PATH + movie.getPosterPath();
        GlideApp.with(context)
                .load(Uri.parse(path))
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(singleViewholder.poster);
    }

    @Override
    public int getItemCount() {
        return Movies.size();
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
