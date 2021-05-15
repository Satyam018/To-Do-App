package com.example.todoapp;

public class model {
    String Task;
    int status;
    int id;

    public model(String task, int status) {
        Task = task;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public model(String task, int status, int id) {
        Task = task;
        this.status = status;
        this.id = id;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        Task = task;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
