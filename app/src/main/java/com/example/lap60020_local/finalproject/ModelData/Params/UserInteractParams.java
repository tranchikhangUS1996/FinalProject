package com.example.lap60020_local.finalproject.ModelData.Params;

public class UserInteractParams {
    private Boolean value;
    private String sessionId;
    private int id;

    public UserInteractParams(Boolean value, String sessionId, int id) {
        this.value = value;
        this.sessionId = sessionId;
        this.id = id;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
