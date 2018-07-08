package com.example.lap60020_local.finalproject.Ui.Adapter;

import android.content.Intent;
import android.view.View;

import com.example.lap60020_local.finalproject.ModelData.Params.GenreParams;
import com.example.lap60020_local.finalproject.Ui.DetailActivity;
import com.example.lap60020_local.finalproject.Ui.WatchListActivity;
import com.example.lap60020_local.finalproject.ViewModel.DetailViewModel;

import org.greenrobot.eventbus.EventBus;

class onCardClick implements View.OnClickListener {

    private int id;

    public onCardClick(Integer id) {
        this.id = id;
    }

    @Override
    public void onClick(View v) {
        DetailViewModel.setId(id);
        Intent intent = new Intent(v.getContext(), DetailActivity.class);
        v.getContext().startActivity(intent);
    }
}
