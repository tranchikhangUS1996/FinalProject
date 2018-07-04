package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.Account;
import com.example.lap60020_local.finalproject.ModelData.Repository.AccountRepository.IAccountRepository;

import io.reactivex.Observable;

public class AccountViewModel {
    private IAccountRepository accountRepository;

    public AccountViewModel(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Observable<Account> getAccountDetail(String sessionID) {
        return accountRepository.getAccountDetail(sessionID);
    }
}
