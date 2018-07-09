package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.FavouriteResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.UserInteractParams;
import com.example.lap60020_local.finalproject.ModelData.Repository.UserInteractRepository.AddToWatchListRepository;

import io.reactivex.Observable;

public class AddToWatchListViewModel {

    private AddToWatchListRepository addToWatchListRepository;

    public AddToWatchListViewModel(AddToWatchListRepository addToWatchListRepository) {
        this.addToWatchListRepository = addToWatchListRepository;
    }

    public Observable<FavouriteResponse> add(UserInteractParams params) {
        if(params.getSessionId().isEmpty()) {
            return Observable.error(new Throwable("Login to add"));
        }
        else {
            return addToWatchListRepository.add(params);
        }
    }
}
