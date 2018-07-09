package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.RateResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.UserRatingParams;
import com.example.lap60020_local.finalproject.ModelData.Repository.UserInteractRepository.RateRepository;

import io.reactivex.Observable;

public class RateViewModel {

    private RateRepository rateRepository;

    public RateViewModel(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public Observable<RateResponse> rate(UserRatingParams params) {
        if(params.getSessionID().isEmpty()) {
            return Observable.error(new Throwable("Login to rate"));
        }
        return rateRepository.rate(params);
    }
}
