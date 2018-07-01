package com.example.lap60020_local.finalproject.ModelData.Entity;

import com.google.gson.annotations.SerializedName;

public class Avatar {
    @SerializedName("gravatar")
    public Gravatar gravatar;

    public Avatar(Gravatar gravatar) {
        this.gravatar = gravatar;
    }

    public Gravatar getGravatar() {
        return gravatar;
    }

    public void setGravatar(Gravatar gravatar) {
        this.gravatar = gravatar;
    }
}
