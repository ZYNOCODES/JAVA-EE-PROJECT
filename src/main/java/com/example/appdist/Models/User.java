package com.example.appdist.Models;

public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String type;

    public User(int id, String email, String password, String name, String phone, String type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

