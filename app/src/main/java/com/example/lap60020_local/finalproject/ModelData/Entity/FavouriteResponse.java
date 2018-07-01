package com.example.lap60020_local.finalproject.ModelData.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavouriteResponse {
    @SerializedName("status_code")
    @Expose
    public Integer statusCode;
    @SerializedName("status_message")
    @Expose
    public String statusMessage;
}
