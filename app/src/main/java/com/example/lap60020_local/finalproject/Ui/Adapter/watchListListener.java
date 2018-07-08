package com.example.lap60020_local.finalproject.Ui.Adapter;

import android.content.Intent;
import android.view.View;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Params.GenreParams;
import com.example.lap60020_local.finalproject.Ui.DetailActivity;
import com.example.lap60020_local.finalproject.Ui.WatchListActivity;
import com.example.lap60020_local.finalproject.ViewModel.DetailViewModel;

import org.greenrobot.eventbus.EventBus;

class watchListListener implements View.OnClickListener {
    Movie movie;
    public watchListListener(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), WatchListActivity.class);
        intent.putExtra("Type", "Genre");
        EventBus.getDefault().postSticky(new GenreParams(movie.getId()));
        v.getContext().startActivity(intent);
    }
}
