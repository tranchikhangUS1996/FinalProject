package com.example.lap60020_local.finalproject.Ui.Adapter;

import android.content.Intent;
import android.view.View;

import com.example.lap60020_local.finalproject.ModelData.Params.GenreMovieParams;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.Ui.WatchListActivity;

import org.greenrobot.eventbus.EventBus;

class OnGenreCardClick implements View.OnClickListener {
    private Integer id;
    public OnGenreCardClick(Integer id) {
        this.id = id;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), WatchListActivity.class);
        intent.putExtra("Type", "Genre");
        Params genreMovieParams = new GenreMovieParams(id);
        EventBus.getDefault().postSticky(genreMovieParams);
        v.getContext().startActivity(intent);
    }
}
