package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.Authentication;
import com.example.lap60020_local.finalproject.ModelData.Entity.AuthenticationSessionID;
import com.example.lap60020_local.finalproject.ModelData.Entity.AuthenticationWithLogin;
import com.example.lap60020_local.finalproject.ModelData.Params.LoginParams;
import com.example.lap60020_local.finalproject.ModelData.Repository.LoginRepository.ILoginRepository;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Call;

public class LoginViewModel {

    private ILoginRepository loginRepository;
    private LoginParams mParam;

    public LoginViewModel(ILoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    public Observable<AuthenticationSessionID> login(LoginParams params) {

        return Observable.just(params).filter(p1 -> mParam == null || !mParam.getUserName().equals(p1.getUserName()) || !mParam.getPassword().equals(p1.getPassword()))
                .flatMap(p -> {
                    mParam = p;
                    Call<Authentication> call = loginRepository.getToken();
                    Authentication authentication = call.execute().body();
                    if (authentication != null && authentication.success) {
                        Call<AuthenticationWithLogin> al = loginRepository.getLoginToken(authentication.requestToken, p);
                        AuthenticationWithLogin authenticationWithLogin = al.execute().body();
                        if (authenticationWithLogin != null && authenticationWithLogin.success) {
                            Call<AuthenticationSessionID> as = loginRepository.getSessionId(authenticationWithLogin.requestToken);
                            AuthenticationSessionID authenticationSessionID = as.execute().body();
                            if (authenticationSessionID != null && authenticationSessionID.success) {
                                return Observable.just(authenticationSessionID);
                            }
                        }
                    }
                    mParam = null;
                    return Observable.error(new Throwable("login fail"));
                });
    }
}
