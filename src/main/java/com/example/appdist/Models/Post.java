package com.example.appdist.Models;

public class Post {
    private int id;
    private int collection;
    private String title;
    private String description;
    private String image;
    private boolean vote;

    public Post(int id, int collection, String title, String description, String image) {
        this.id = id;
        this.collection = collection;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public Post(int collection, String title, String description, String image) {
        this.collection = collection;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public Post(int id) {
        this.id = id;
    }

    public Post(int id, int collection, String title, String description, String image, boolean vote) {
        this.id = id;
        this.collection = collection;
        this.title = title;
        this.description = description;
        this.image = image;
        this.vote = vote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }
}
