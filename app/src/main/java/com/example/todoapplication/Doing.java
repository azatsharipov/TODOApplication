package com.example.todoapplication;

public class Doing {
    private String header;
    private String text;
    private float time;


    public Doing(String header, String text) {
        this.header = header;
        this.text = text;
        this.time = 0f;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
