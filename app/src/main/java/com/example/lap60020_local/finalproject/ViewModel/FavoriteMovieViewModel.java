package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Params.Params;

public class FavoriteMovieViewModel extends UserMovieViewModel {
    @Override
    public String specifyType() {
        return "Favorite";
    }

    @Override
    public Params specifyParams(Params params) {
        return params;
    }
}
