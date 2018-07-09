package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.FavouriteResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.UserInteractParams;
import com.example.lap60020_local.finalproject.ModelData.Repository.UserInteractRepository.MaskAsFovoriteRepository;

import io.reactivex.Observable;

public class MaskAsFavoriteViewModel {

    private MaskAsFovoriteRepository maskAsFovoriteRepository;

    public MaskAsFavoriteViewModel(MaskAsFovoriteRepository maskAsFovoriteRepository) {
        this.maskAsFovoriteRepository = maskAsFovoriteRepository;
    }

    public Observable<FavouriteResponse> mask(UserInteractParams params) {
        if(params.getSessionId().isEmpty()) {
            return Observable.error(new Throwable("login to mask as fovorite"));
        }
        return maskAsFovoriteRepository.mask(params);
    }
}
