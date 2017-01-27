package ru.osll.goodtravel.models;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import ru.osll.goodtravel.enums.TypeOfGroupEnum;

/**
 * Created by denis on 11/26/16.
 */

public class Place extends RealmObject {

    @PrimaryKey
    private String name;
    private String Description;

    private String PlaceName;
    private String address;

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }

    private String coordinate;

    private int price;
    private String srcToImg;
    @Ignore
    private String typeOfGroup;

    private PlaceCategory category;

    public PlaceCategory getCategory() {
        return category;
    }

    public void setCategory(PlaceCategory category) {
        this.category = category;
    }


    public Place() {
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
    }

    public Place(String name, int price, String place) {
        this.name = name;
        this.price = price;
        this.PlaceName = place;
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
    }

    public Place(String name, int price, String place, PlaceCategory category) {
        this.name = name;
        this.price = price;
        this.PlaceName = place;
        this.category = category;
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
    }
    public Place(String name, int price, String place,String coords, PlaceCategory category) {
        this.name = name;
        this.price = price;
        this.PlaceName = place;
        this.category = category;
        coordinate = coords;
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
    }

    public Place(String name, int price, String place,String coords,
                 PlaceCategory category, TypeOfGroupEnum typeOfGroupEnum) {
        this.name = name;
        this.price = price;
        this.PlaceName = place;
        this.category = category;
        coordinate = coords;
        this.typeOfGroup = typeOfGroupEnum.toString();
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
    }

    public TypeOfGroupEnum getTypeOfGroup() {
        return TypeOfGroupEnum.valueOf(typeOfGroup);
    }

    public void setTypeOfGroup(TypeOfGroupEnum typeOfGroupEnum) {
        this.typeOfGroup = typeOfGroupEnum.toString();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSrcToImg() {

        return srcToImg;
    }

    public void setSrcToImg(String srcToImg) {
        this.srcToImg = srcToImg;
    }

    public static Place getByName(String name) {
        return Realm
                .getDefaultInstance()
                .where(Place.class)
                .equalTo("name", name)
                .findFirst();
    }

    public static List<Place> getAll(Realm realm)
    {
        return realm.where(Place.class).findAll();
    }

    public static List<Place> getServicesWithCategory(PlaceCategory placeCategory)
    {
        Realm realm = Realm.getDefaultInstance();
        List<Place> places = realm
                .where(Place.class)
                .equalTo("category", placeCategory.getName())
                .findAll();

        return places;
    }

    public static List<Place> getServices(PlaceCategory placeCategory, int price)
    {
        List<Place> servicesList = getServicesWithCategory(placeCategory);
        List<Place> tempPlaceList = new ArrayList<>();

        for(int i = 0; i < servicesList.size(); i++)
        {
            Place place = servicesList.get(i);
            if(place.getPrice() <= price) tempPlaceList.add(place);
        }

        return tempPlaceList;
    }
}
