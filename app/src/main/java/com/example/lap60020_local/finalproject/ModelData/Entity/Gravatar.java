package com.example.lap60020_local.finalproject.ModelData.Entity;

import com.google.gson.annotations.SerializedName;

public class Gravatar {

    @SerializedName("hash")
    public String hash;

    public Gravatar(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
