package com.example.lap60020_local.finalproject.ModelData.Params;

public class GenreMovieParams implements Params {

    private int page;
    private int type;
    private int id;

    public GenreMovieParams(int id) {
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
