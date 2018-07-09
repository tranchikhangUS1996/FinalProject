package com.example.lap60020_local.finalproject.ModelData.Params;

public class UserRatingParams {
    private String sessionID;
    private int rate;
    private int id;

    public UserRatingParams(String sessionID, int rate, int id) {
        this.sessionID = sessionID;
        this.rate = rate;
        this.id = id;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
