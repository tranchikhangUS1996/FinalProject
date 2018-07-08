package com.example.lap60020_local.finalproject.ModelData.Repository.AccountRepository;

import com.example.lap60020_local.finalproject.ModelData.Entity.Account;
import com.example.lap60020_local.finalproject.ModelData.retrofit.AuthenticationAPI;

import io.reactivex.Observable;

public class AccountRepository implements  IAccountRepository {

    private AuthenticationAPI authenticationAPI;
    private final String apiKey;

    public AccountRepository(AuthenticationAPI authenticationAPI, String apiKey) {
        this.authenticationAPI = authenticationAPI;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<Account> getAccountDetail(String sessionID) {
        return authenticationAPI.getAccountDetail(sessionID, apiKey);
    }
}
