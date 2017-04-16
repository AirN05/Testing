package com.example.models;

import com.google.gson.Gson;

import java.util.List;

public class Dataset {

    private long global_id;
    private String DepartamentalAffiliation;
    private String AdmArea;
    private String  District;
    private String Location;
    private String DogParkArea;
    private List Elements;
    private String Lighting;
    private String Fencing;
    private List Photo;
    private List WorkingHours;



    private DataObj geoData;

    public DataObj getGeoData() {
        return geoData;
    }

    public void setGeoData(DataObj geoData) {
        this.geoData = geoData;
    }
    public String getLighting() {
        return Lighting;
    }

    public void setLighting(String lighting) {
        Lighting = lighting;
    }

    public String getFencing() {
        return Fencing;
    }

    public void setFencing(String fencing) {
        Fencing = fencing;
    }

    public List getPhoto() {
        return Photo;
    }

    public void setPhoto(List photo) {
        Photo = photo;
    }

    public List getWorkingHours() {
        return WorkingHours;
    }

    public void setWorkingHours(List workingHours) {
        WorkingHours = workingHours;
    }


    public long getGlobal_id() {
        return global_id;
    }

    public void setGlobal_id(long global_id) {
        this.global_id = global_id;
    }

    public String getDepartamentalAffiliation() {
        return DepartamentalAffiliation;
    }

    public void setDepartamentalAffiliation(String departamentalAffiliation) {
        DepartamentalAffiliation = departamentalAffiliation;
    }

    public String getAdmArea() {
        return AdmArea;
    }

    public void setAdmArea(String admArea) {
        AdmArea = admArea;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDogParkArea() {
        return DogParkArea;
    }

    public void setDogParkArea(String dogParkArea) {
        DogParkArea = dogParkArea;
    }

    public List getElements() {
        return Elements;
    }

    public void setElements(List elements) {
        Elements = elements;
    }
}





