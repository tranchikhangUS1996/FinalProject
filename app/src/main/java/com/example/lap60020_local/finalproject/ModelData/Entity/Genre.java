package com.example.lap60020_local.finalproject.ModelData.Entity;

import com.google.gson.annotations.SerializedName;

public class Genre {

    @SerializedName("id")
    public Integer id;
    @SerializedName("name")
    public String name;

    public Genre(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
