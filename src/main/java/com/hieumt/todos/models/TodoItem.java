package com.hieumt.todos.models;

public class TodoItem {

    private String id;
    private String title;
    private String type;
    private String tag;
    private String list;
    private String description;
    private boolean completed;

    public TodoItem() {
    }

    public TodoItem(String id, String title, String type, String tag, String list, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.tag = tag;
        this.list = list;
        this.description = description;
        this.completed = completed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
