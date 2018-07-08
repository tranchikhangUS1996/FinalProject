package com.example.lap60020_local.finalproject.ModelData.Params;

public class GenreParams implements Params {
    private int id;

    public GenreParams(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public void setPage(int page) {

    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public void setType(int type) {

    }
}
