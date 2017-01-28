package ru.osll.goodtravel.models.DAO;

import java.util.ArrayList;
import java.util.List;

import ru.osll.goodtravel.enums.TypeOfGroupEnum;


public class Place {
    private long id;
    private String name;
    private String description;

    private String placeName;
    private String address;
    private String coordinate;
    private long price;
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

    public Place(long id, String name, String description,
                 String placeName,
                 String address, String coordinate, long price, String srcToImg,
                 String typeOfGroup, PlaceCategory category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.placeName = placeName;
        this.address = address;
        this.coordinate = coordinate;
        this.price = price;
        this.srcToImg = srcToImg;
        this.typeOfGroup = typeOfGroup;
        this.category = category;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSrcToImg() {

        return srcToImg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setSrcToImg(String srcToImg) {
        this.srcToImg = srcToImg;
    }
}
