package ru.osll.goodtravel.models.DAO;

import java.util.ArrayList;
import java.util.List;

import ru.osll.goodtravel.enums.TypeOfGroupEnum;


public class Place {

    private String name;
    private String description;

    private String placeName;
    private String address;
    private String coordinate;
    private int price;
    private String srcToImg;
    private String typeOfGroup;
    private PlaceCategory category;

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
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

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
        this.placeName = place;
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
    }

    public Place(String name, int price, String place, PlaceCategory category) {
        this.name = name;
        this.price = price;
        this.placeName = place;
        this.category = category;
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
    }
    public Place(String name, int price, String place,String coords, PlaceCategory category) {
        this.name = name;
        this.price = price;
        this.placeName = place;
        this.category = category;
        coordinate = coords;
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
    }

    public Place(String name, int price, String place,String coords,
                 PlaceCategory category, TypeOfGroupEnum typeOfGroupEnum) {
        this.name = name;
        this.price = price;
        this.placeName = place;
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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        // TODO: 28.01.17 Реализовать
//        return Realm
//                .getDefaultInstance()
//                .where(Place.class)
//                .equalTo("name", name)
//                .findFirst();
        return null;
    }

    public static List<Place> getAll()
    {
        // TODO: 28.01.17 Реализовать
        return null;
    }

    public static List<Place> getServicesWithCategory(PlaceCategory placeCategory)
    {
        // TODO: 28.01.17 Реализовать
//        Realm realm = Realm.getDefaultInstance();
//        List<Place> places = realm
//                .where(Place.class)
//                .equalTo("category", placeCategory.getName())
//                .findAll();

        return null;
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
