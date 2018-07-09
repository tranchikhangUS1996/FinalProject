package com.example.lap60020_local.finalproject.Ui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lap60020_local.finalproject.ModelData.Entity.Genre;
import com.example.lap60020_local.finalproject.R;

import java.util.List;

public class GenrelistAdapter extends RecyclerView.Adapter<GenrelistAdapter.myViewholder> {

    private List<Genre> genres;
    private Context context;

    public GenrelistAdapter(List<Genre> genres, Context context) {
        this.genres = genres;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(context).inflate(R.layout.genre_list_layout,parent,false);
        return new myViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewholder holder, int position) {
        Genre genre = genres.get(position);
        holder.item.setText(genre.getName());
        holder.cardView.setOnClickListener(new OnGenreCardClick(genre.getId()));
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    class myViewholder extends RecyclerView.ViewHolder{

        TextView item;
        CardView cardView;
        myViewholder(CardView itemView) {
            super(itemView);
            cardView = itemView;
            item = itemView.findViewById(R.id.genrelist_item_id);
        }
    }
}
