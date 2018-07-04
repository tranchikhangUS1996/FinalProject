package com.example.lap60020_local.finalproject.ModelData.Params;

public class RecommendParams implements Params {
    private int page;
    private int type;
    private int id;

    public RecommendParams(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }
}
