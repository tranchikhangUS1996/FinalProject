package com.example.lap60020_local.finalproject.ModelData.Params;

public class UserMovieParam implements Params {

    private int type;
    private int page;
    private String sessionID;

    public UserMovieParam(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionID() {
        return sessionID;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }
}
