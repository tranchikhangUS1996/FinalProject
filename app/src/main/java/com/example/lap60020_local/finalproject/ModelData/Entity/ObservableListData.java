package com.example.lap60020_local.finalproject.ModelData.Entity;

import java.util.List;

public class ObservableListData {
    List<Movie> data;
    int seePossition;

    public ObservableListData(List<Movie> data, int seePossition) {
        this.data = data;
        this.seePossition = seePossition;
    }

    public int getSeePossition() {
        return seePossition;
    }

    public void setSeePossition(int seePossition) {
        this.seePossition = seePossition;
    }

    public List<Movie> getData() {

        return data;
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }
}
