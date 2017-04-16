package com.example.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OurResponse {

    @SerializedName("global_id")
    private long global_id;

    @SerializedName("Number")
    private int Number;

    @SerializedName("Cells")
    private Dataset Cells;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public long getGlobal_id() {
        return global_id;
    }

    public void setGlobal_id(long global_id) {
        this.global_id = global_id;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public Dataset getCells() {
        return Cells;
    }

    public void setCells(Dataset cells) {
        Cells = cells;
    }
}



