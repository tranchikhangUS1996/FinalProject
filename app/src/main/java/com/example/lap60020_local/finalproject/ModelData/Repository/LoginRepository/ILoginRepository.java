package com.example.lap60020_local.finalproject.ModelData.Repository.LoginRepository;

import com.example.lap60020_local.finalproject.ModelData.Entity.Authentication;
import com.example.lap60020_local.finalproject.ModelData.Entity.AuthenticationSessionID;
import com.example.lap60020_local.finalproject.ModelData.Entity.AuthenticationWithLogin;
import com.example.lap60020_local.finalproject.ModelData.Params.LoginParams;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;

import io.reactivex.Observable;

public interface ILoginRepository {

    Observable<Authentication> getToken();

    Observable<AuthenticationWithLogin> getLoginToken(String token, LoginParams userParams);

    Observable<AuthenticationSessionID> getSessionId(String sessionId);
}
