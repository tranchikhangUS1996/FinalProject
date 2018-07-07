package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.Authentication;
import com.example.lap60020_local.finalproject.ModelData.Entity.AuthenticationSessionID;
import com.example.lap60020_local.finalproject.ModelData.Entity.AuthenticationWithLogin;
import com.example.lap60020_local.finalproject.ModelData.Params.LoginParams;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Repository.LoginRepository.ILoginRepository;

import io.reactivex.Observable;

public class LoginViewModel {

    private ILoginRepository loginRepository;

    public LoginViewModel(ILoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

//    public Observable<AuthenticationSessionID> login(LoginParams params) {
//
//        Observable<LoginParams> paramsObservable = Observable.just(params);
//        Observable<Authentication> authenticationObservable = loginRepository.getToken();
//        return Observable.zip(paramsObservable,authenticationObservable, (p,t)->{
//            return loginRepository.getLoginToken(t.requestToken,p);
//        }).flatMap(observable ->{
//
//        });
//    }

}
