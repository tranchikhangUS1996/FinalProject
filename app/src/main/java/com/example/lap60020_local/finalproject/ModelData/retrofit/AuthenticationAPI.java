package com.example.lap60020_local.finalproject.ModelData.retrofit;

import com.example.lap60020_local.finalproject.ModelData.Entity.Account;
import com.example.lap60020_local.finalproject.ModelData.Entity.Authentication;
import com.example.lap60020_local.finalproject.ModelData.Entity.AuthenticationSessionID;
import com.example.lap60020_local.finalproject.ModelData.Entity.AuthenticationWithLogin;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthenticationAPI {
    @GET("authentication/token/new")
    Call<Authentication> getToken(@Query("api_key") String apiKey);

    @GET("authentication/token/validate_with_login")
    Call<AuthenticationWithLogin> getLoginToken(@Query("username") String userName,
                                                      @Query("password") String password,
                                                      @Query("request_token") String token,
                                                      @Query("api_key") String apiKey );
    @GET("authentication/session/new")
    Call<AuthenticationSessionID> getSessionID(@Query("request_token") String requesToken,
                                                     @Query("api_key") String apiKey);

    @GET("account")
    Observable<Account> getAccountDetail(@Query("session_id") String sessionId,
                                         @Query("api_key") String apiKey);
}
