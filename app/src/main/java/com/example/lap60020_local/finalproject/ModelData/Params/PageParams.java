package com.example.lap60020_local.finalproject.ModelData.Params;

public class PageParams implements Params {
    private int page;
    private int type;

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

    @Override
    public int getPage() {
        return page;
    }
}
