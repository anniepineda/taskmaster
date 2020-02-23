package com.anniepineda.taskmaster.models;

public class Task {
    private String title;
    private String description;
    private String state;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.state ="New Task";
    }

    public Task(){

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}

