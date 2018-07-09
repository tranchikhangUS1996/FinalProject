package com.example.lap60020_local.finalproject.Ui.Adapter;

import android.content.Intent;
import android.view.View;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.Ui.DetailActivity;
import com.example.lap60020_local.finalproject.ViewModel.DetailViewModel;

class onCardClick implements View.OnClickListener {

    private Movie id;

    public onCardClick(Movie id) {
        this.id = id;
    }

    @Override
    public void onClick(View v) {
        DetailViewModel.setId(id);
        Intent intent = new Intent(v.getContext(), DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        v.getContext().startActivity(intent);
    }
}
