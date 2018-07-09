package com.example.lap60020_local.finalproject.ModelData.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WatchlistBody {
    @SerializedName("media_type")
    @Expose
    private String media_type = "movie";
    @SerializedName("media_id")
    @Expose
    private Integer media_id;
    @SerializedName("watchlist")
    @Expose
    private Boolean watchlist;

    public WatchlistBody(Integer media_id, Boolean watchlist) {
        this.media_id = media_id;
        this.watchlist = watchlist;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public Integer getMedia_id() {
        return media_id;
    }

    public void setMedia_id(Integer media_id) {
        this.media_id = media_id;
    }

    public Boolean getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Boolean watchlist) {
        this.watchlist = watchlist;
    }
}
