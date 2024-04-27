package com.example.appdist.Models;
import java.sql.Date;

public class Collection {
    private int id;
    private String name;
    private String description;
    private String end_date;

    public Collection(int id, String name, String description, String end_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.end_date = end_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
