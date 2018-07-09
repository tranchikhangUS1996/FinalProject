package com.example.lap60020_local.finalproject.ViewModel;

import android.content.Intent;

import com.example.lap60020_local.finalproject.ModelData.Entity.MyCallback;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Params.UserMovieParam;
import com.example.lap60020_local.finalproject.Ui.LoginActivity;
import com.example.lap60020_local.finalproject.Ui.WatchListActivity;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;

public abstract class UserMovieViewModel {

    public abstract String specifyType();
    public abstract Params specifyParams(Params params);
    private Params mParams;

    public Observable<MyCallback> userClick(Params params) {
        this.mParams = params;
        UserMovieParam userMovieParam = (UserMovieParam) params;
        if(userMovieParam.getSessionID().isEmpty()) {
            MyCallback myCallback = context -> {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            };
            return Observable.just(myCallback);
        }

        MyCallback myCallback = context -> {
            Intent intent = new Intent(context, WatchListActivity.class);
            String type = specifyType();
            intent.putExtra("Type", type);
            context.startActivity(intent);
            mParams = specifyParams(mParams);
            EventBus.getDefault().postSticky(mParams);
        };
        return Observable.just(myCallback);
    }

}
