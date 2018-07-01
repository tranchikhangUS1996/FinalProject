package com.example.lap60020_local.finalproject.ModelData.Entity;

import com.google.gson.annotations.SerializedName;

public class AuthenticationWithLogin {
    @SerializedName("success")
    public Boolean success;
    @SerializedName("request_token")
    public String requestToken;

    public AuthenticationWithLogin(Boolean success, String requestToken) {
        this.success = success;
        this.requestToken = requestToken;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }
}
