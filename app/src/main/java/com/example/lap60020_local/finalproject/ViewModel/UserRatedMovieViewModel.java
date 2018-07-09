package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Params.Params;

public class UserRatedMovieViewModel extends UserMovieViewModel {
    @Override
    public String specifyType() {
        return "Rated";
    }

    @Override
    public Params specifyParams(Params params) {
        return params;
    }
}
