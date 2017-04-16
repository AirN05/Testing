package com.example.models;

import com.google.gson.Gson;

import java.util.List;

public class Response<T> {

    private List<T> Items;
    private long count;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public List<T> getItems() {
        return Items;
    }

    public void setItems(List<T> items) {
        Items = items;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
