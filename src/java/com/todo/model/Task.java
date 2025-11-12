package com.todo.model;

public class Task {
    
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false; 
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        String status = isCompleted ? "[X]" : "[ ]";
        return status + " " + description;
    }
    
    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}