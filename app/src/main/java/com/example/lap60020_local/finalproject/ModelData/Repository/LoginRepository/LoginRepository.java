package com.example.lap60020_local.finalproject.ModelData.Repository.LoginRepository;

import com.example.lap60020_local.finalproject.ModelData.Entity.Authentication;
import com.example.lap60020_local.finalproject.ModelData.Entity.AuthenticationSessionID;
import com.example.lap60020_local.finalproject.ModelData.Entity.AuthenticationWithLogin;
import com.example.lap60020_local.finalproject.ModelData.Params.LoginParams;
import com.example.lap60020_local.finalproject.ModelData.retrofit.AuthenticationAPI;

import retrofit2.Call;

public class LoginRepository implements ILoginRepository{

    private AuthenticationAPI authenticationAPI;
    private final String apiKey;
    public LoginRepository(AuthenticationAPI authenticationAPI, String apiKey ) {
        this.authenticationAPI = authenticationAPI;
        this.apiKey = apiKey;
    }

    @Override
    public Call<Authentication> getToken() {
        return authenticationAPI.getToken(apiKey);
    }

    @Override
    public Call<AuthenticationWithLogin> getLoginToken(String token, LoginParams userParams) {
        return authenticationAPI.getLoginToken(userParams.getUserName(),userParams.getPassword(),token,apiKey);
    }

    @Override
    public Call<AuthenticationSessionID> getSessionId(String requestToken) {
        return authenticationAPI.getSessionID(requestToken,apiKey);
    }
}
