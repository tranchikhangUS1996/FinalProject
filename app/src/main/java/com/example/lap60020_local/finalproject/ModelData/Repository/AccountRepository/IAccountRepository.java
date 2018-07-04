package com.example.lap60020_local.finalproject.ModelData.Repository.AccountRepository;

import com.example.lap60020_local.finalproject.ModelData.Entity.Account;

import io.reactivex.Observable;

public interface IAccountRepository {
    Observable<Account> getAccountDetail(String sessionID);

}
