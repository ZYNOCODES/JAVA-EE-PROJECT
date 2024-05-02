package com.example.appdist.Models;

public class Vote {
    private int id;
    private int post;
    private int user;
    private String date;

    public Vote(int id, int post, int user, String date) {
        this.id = id;
        this.post = post;
        this.user = user;
        this.date = date;
    }

    public Vote(int post, int user, String date) {
        this.post = post;
        this.user = user;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
