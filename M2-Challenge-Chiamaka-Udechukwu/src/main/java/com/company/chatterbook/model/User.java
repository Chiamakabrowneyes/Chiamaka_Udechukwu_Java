package com.company.chatterbook.model;

import java.util.List;

//The User model object should contain the following two instance variables:
//name (string)
//chatterPosts (list of ChatterPost objects)

public class User {
    public String name;
    public List<ChatterPost> chatterPosts;
    public User(String name) {
        this.name = name;
    }

    public User(String name, List<ChatterPost> chatterPosts) {
        this.name = name;
        this.chatterPosts = chatterPosts;
    }

    public String getName() {
        return name;
    }

    public List<ChatterPost> getChatterPosts() {
        return chatterPosts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChatterPosts(List<ChatterPost> chatterPosts) {
        this.chatterPosts = chatterPosts;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", chatterPosts=" + chatterPosts +
                '}';
    }
}
