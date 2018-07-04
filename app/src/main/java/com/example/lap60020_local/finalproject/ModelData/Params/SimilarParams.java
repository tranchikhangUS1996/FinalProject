package com.example.lap60020_local.finalproject.ModelData.Params;

public class SimilarParams implements Params {

    private int page;
    private int type;
    private int id;

    public SimilarParams(int id) {
        this.id = id;
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
