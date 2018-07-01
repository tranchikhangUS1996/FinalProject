package com.example.lap60020_local.finalproject.ModelData.Entity;

import com.google.gson.annotations.SerializedName;

public class AuthenticationSessionID {
    @SerializedName("success")
    public Boolean success;
    @SerializedName("session_id")
    public String sessionId;

    public AuthenticationSessionID(Boolean success, String sessionId) {
        this.success = success;
        this.sessionId = sessionId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
