package com.example.lap60020_local.finalproject.ModelData.Entity;

import com.google.gson.annotations.SerializedName;

public class Account {
    @SerializedName("avatar")
    public Avatar avatar;
    @SerializedName("id")
    public Integer id;
    @SerializedName("iso_639_1")
    public String iso6391;
    @SerializedName("iso_3166_1")
    public String iso31661;
    @SerializedName("name")
    public String name;
    @SerializedName("include_adult")
    public Boolean includeAdult;
    @SerializedName("username")
    public String username;

    public Account(Avatar avatar,
                   Integer id,
                   String iso6391,
                   String iso31661,
                   String name,
                   Boolean includeAdult,
                   String username) {

        this.avatar = avatar;
        this.id = id;
        this.iso6391 = iso6391;
        this.iso31661 = iso31661;
        this.name = name;
        this.includeAdult = includeAdult;
        this.username = username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIncludeAdult() {
        return includeAdult;
    }

    public void setIncludeAdult(Boolean includeAdult) {
        this.includeAdult = includeAdult;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
