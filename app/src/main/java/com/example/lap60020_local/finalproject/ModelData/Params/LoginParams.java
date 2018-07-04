package com.example.lap60020_local.finalproject.ModelData.Params;

public class LoginParams {
    private String userName;
    private String password;

    public LoginParams(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
