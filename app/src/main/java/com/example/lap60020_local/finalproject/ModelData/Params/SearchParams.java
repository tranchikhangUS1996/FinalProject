package com.example.lap60020_local.finalproject.ModelData.Params;

public class SearchParams implements Params {
    int page;
    int type;
    String text;

    public SearchParams(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
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
