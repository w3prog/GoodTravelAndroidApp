package ru.osll.goodtravel.models;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import ru.osll.goodtravel.enums.PlaceTypeEnum;

public class Place extends RealmObject {

    @PrimaryKey
    private long id;

    private String name;
    private String Description;
    private Address address;
    private String image;
    @Ignore
    private String category;

    public RealmList<Service> getServices() {
        return services;
    }

    public void setServices(RealmList<Service> services) {
        this.services = services;
    }

    private RealmList<Service> services;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        if (Description.isEmpty()) {
            return "Без описания";
        }
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PlaceTypeEnum getCategory() {
        return PlaceTypeEnum.valueOf(category);
    }

    public void setCategory(PlaceTypeEnum category) {
        this.category = category.toString();
    }

    public static Place getByPrimaryKey(Realm realm, int id) {
        return realm.where(Place.class).equalTo("id", id).findFirst();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Place() {
    }

}
