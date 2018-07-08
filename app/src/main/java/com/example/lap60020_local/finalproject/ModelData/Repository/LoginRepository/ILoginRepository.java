package com.example.lap60020_local.finalproject.ModelData.Repository.LoginRepository;

import com.example.lap60020_local.finalproject.ModelData.Entity.Authentication;
import com.example.lap60020_local.finalproject.ModelData.Entity.AuthenticationSessionID;
import com.example.lap60020_local.finalproject.ModelData.Entity.AuthenticationWithLogin;
import com.example.lap60020_local.finalproject.ModelData.Params.LoginParams;

import io.reactivex.Observable;
import retrofit2.Call;

public interface ILoginRepository {

    Call<Authentication> getToken();

    Call<AuthenticationWithLogin> getLoginToken(String token, LoginParams userParams);

    Call<AuthenticationSessionID> getSessionId(String sessionId);
}
