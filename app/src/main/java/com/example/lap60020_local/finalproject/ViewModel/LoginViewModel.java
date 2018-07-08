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
    private BehaviorSubject<LoginParams> subject = BehaviorSubject.create();
    private LoginParams mParam;

    public LoginViewModel(ILoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void login(LoginParams params) {
        subject.onNext(params);
    }

    public Observable<AuthenticationSessionID> loginStream() {
        return subject.skipWhile(param->{
            mParam = param;
            return mParam != null && (mParam.getUserName().equals(param.getUserName()) && mParam.getPassword().equals(param.getPassword()));}
            ).switchMap(p -> {
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
            return Observable.error(new Throwable("login fail"));
        });
    }
}
