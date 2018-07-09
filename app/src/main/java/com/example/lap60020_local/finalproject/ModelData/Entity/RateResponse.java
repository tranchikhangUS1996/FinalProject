package com.example.lap60020_local.finalproject.ModelData.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RateResponse {
    @SerializedName("status_code")
    @Expose
    public Integer statusCode;
    @SerializedName("status_message")
    @Expose
    public String statusMessage;

    public RateResponse(Integer statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
