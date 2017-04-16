package com.example.models;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("Id")
    @Expose
    private long id;

    @SerializedName("IdentificationNumber")
    @Expose
    private String IdentificationNumber;

    @SerializedName("categoryCaption")
    @Expose
    private String categoryCaption;

    @SerializedName("DepartmentId")
    @Expose
    private long DepartmentId;

    @SerializedName("departmentCaption")
    @Expose
    private String departmentCaption;

    @SerializedName("Caption")
    @Expose
    private String Caption;

    @SerializedName("FullDescription")
    @Expose
    private String FullDescription;


    @SerializedName("Columns")
    @Expose
    private List<DataObj> columns;

    @SerializedName("ItemsCount")
    @Expose
    private int itemsCount;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public List<DataObj> getColumns() {
        return columns;
    }

    public void setColumns(List<DataObj> columns) {
        this.columns = columns;
    }

    public long getId() {
        return id;
    }

    public String getIdentificationNumber() {
        return IdentificationNumber;
    }

    public String getCategoryCaption() {
        return categoryCaption;
    }

    public long getDepartmentId() {
        return DepartmentId;
    }

    public String getDepartmentCaption() {
        return departmentCaption;
    }

    public String getCaption() {
        return Caption;
    }

    public String getFullDescription() {
        return FullDescription;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdentificationNumber(String identificationNumber) {
        IdentificationNumber = identificationNumber;
    }

    public void setCategoryCaption(String categoryCaption) {
        this.categoryCaption = categoryCaption;
    }

    public void setDepartmentId(long departmentId) {
        DepartmentId = departmentId;
    }

    public void setDepartmentCaption(String departmentCaption) {
        this.departmentCaption = departmentCaption;
    }

    public void setCaption(String caption) {
        Caption = caption;
    }

    public void setFullDescription(String fullDescription) {
        FullDescription = fullDescription;
    }


}
