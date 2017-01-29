package ru.osll.goodtravel.models.DAO;

import java.util.ArrayList;
import java.util.List;


public class PlaceCategory {
    private long id;
    private String name;
    private String strImg;
    private ArrayList<Place> places;

    public PlaceCategory(){ }

    public PlaceCategory(String name) {
        this.name = name;
    }

    public PlaceCategory(String name, String strImg) {
        this.name = name;
        this.strImg = strImg;
    }

    public PlaceCategory(long id, String name, String strImg) {
        this.id = id;
        this.name = name;
        this.strImg = strImg;
    }

    public String getStrImg() {
        return strImg;
    }

    public void setStrImg(String strImg) {
        this.strImg = strImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
