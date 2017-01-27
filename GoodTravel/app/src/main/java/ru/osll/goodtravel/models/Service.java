package ru.osll.goodtravel.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import ru.osll.goodtravel.enums.TypeOfGroupEnum;

/**
 * Created by denis on 11/26/16.
 */

public class Service extends RealmObject {

    @PrimaryKey
    private long id;

    private String name;
    private String Description;
    private Date startDate;
    private Date finishDate;
    private int price;
    private String srcToImg;
    private Place place;
    @Ignore
    private String typeOfGroup;

    public CategoryOfService getCategory() {
        return category;
    }

    public void setCategory(CategoryOfService category) {
        this.category = category;
    }

    private CategoryOfService category;

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Service() {
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
    }

    public Service(String name, int price, Place place) {
        this.name = name;
        this.price = price;
        this.place = place;
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
    }

    public Service(String name, int price, Place place,CategoryOfService category) {
        this.name = name;
        this.price = price;
        this.place = place;
        this.category = category;
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
    }

    public Service(String name, int price, Place place,CategoryOfService category, int id) {
        this.name = name;
        this.price = price;
        this.place = place;
        this.category = category;
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
        this.id = id;
    }

    public Service(String name, int price, Place place,
                   CategoryOfService category,TypeOfGroupEnum typeOfGroupEnum) {
        this.name = name;
        this.price = price;
        this.place = place;
        this.category = category;
        this.typeOfGroup = typeOfGroupEnum.toString();
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
    }

    public Service(String name, int price, Place place,
                   CategoryOfService category,TypeOfGroupEnum typeOfGroupEnum, int id) {
        this.name = name;
        this.price = price;
        this.place = place;
        this.category = category;
        this.typeOfGroup = typeOfGroupEnum.toString();
        typeOfGroup = TypeOfGroupEnum.ALL.toString();
        this.id = id;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSrcToImg(String srcToImg) {
        this.srcToImg = srcToImg;
    }

    public static Service getByPrimaryKey(Realm realm, int id) {
        return realm.where(Service.class).equalTo("id", id).findFirst();
    }

    public static List<Service> getAll(Realm realm)
    {
        return realm.where(Service.class).findAll();
    }

    public static List<Service> getServices(CategoryOfService categoryOfService)
    {
        Realm realm = Realm.getDefaultInstance();
        List<Service> services = realm.where(Service.class).findAll();
        List<Service> tempServices = new ArrayList<>();

        for(Service service : services)
        {
            if(service.getCategory().getName().equals(categoryOfService.getName()))
            {
                tempServices.add(service);
            }
        }

        return tempServices;
    }

    public static List<Service> getServices(Realm realm, CategoryOfService categoryOfService, int price)
    {

        List<Service> servicesList = getServices(realm, categoryOfService);
        List<Service> tempServiceList = new ArrayList<>();

        for(int i = 0; i < servicesList.size(); i++)
        {
            Service service = servicesList.get(i);
            if(service.getPrice() <= price) tempServiceList.add(service);
        }

        return tempServiceList;
    }
}
