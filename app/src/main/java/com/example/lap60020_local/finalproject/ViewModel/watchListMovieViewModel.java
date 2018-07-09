package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Params.Params;

public class watchListMovieViewModel extends UserMovieViewModel {
    @Override
    public String specifyType() {
        return "WatchList";
    }

    @Override
    public Params specifyParams(Params params) {
        return params;
    }
}
