package ru.osll.goodtravel.models;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by denis on 11/26/16.
 */

public class CategoryOfService extends RealmObject {

    @PrimaryKey
    private String name;
    private String strImg;
    private RealmList<Service> services;

    public String getStrImg() {
        return strImg;
    }

    public CategoryOfService(){

    }

    public CategoryOfService(String name, String strImg) {
        this.name = name;
        this.strImg = strImg;
    }

    public CategoryOfService(String name, long id) {
        this.name = name;
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
    
    public RealmList<Service> getServices() {
        return services;
    }

    public void setServices(RealmList<Service> services) {
        this.services = services;
    }

    public static CategoryOfService getByPrimaryKey(Realm realm, int id) {
        return realm.where(CategoryOfService.class).equalTo("id", id).findFirst();
    }

    public static CategoryOfService getByName(Realm realm, String name) {
        return realm.where(CategoryOfService.class).equalTo("name", name).findFirst();
    }

    public static List<CategoryOfService> getAllCategoryOfService(Realm realm)
    {
        return realm.where(CategoryOfService.class).findAll();
    }
}
