package ru.osll.goodtravel.models.DAO;

import java.util.ArrayList;
import java.util.List;


public class PlaceCategory {
    private long id;
    private String name;
    private String strImg;
    private ArrayList<Place> places;

    public PlaceCategory(){ }

    public PlaceCategory(String name, String strImg) {
        this.name = name;
        this.strImg = strImg;
    }

    public PlaceCategory(String name) {
        this.name = name;
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


    public static PlaceCategory getByPrimaryKey(int id) {
//        return Realm.getDefaultInstance()
//                .where(PlaceCategory.class)
//                .equalTo("id", id)
//                .findFirst();
        return null;
    }

    public static PlaceCategory getByName(String name) {
//        return Realm.getDefaultInstance()
//                .where(PlaceCategory.class)
//                .equalTo("name", name)
//                .findFirst();
        return null;
    }

    public static List<PlaceCategory> getAll()
    {
//        return Realm.getDefaultInstance()
//                .where(PlaceCategory.class)
//                .findAll();
        return null;
    }
}
