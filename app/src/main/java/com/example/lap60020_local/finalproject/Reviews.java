package com.example.lap60020_local.finalproject;

public class Reviews {
    private String ten;
    private String content;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Reviews(String ten, String content) {
        this.ten = ten;
        this.content = content;
    }
}
