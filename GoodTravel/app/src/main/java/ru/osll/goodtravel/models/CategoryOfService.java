package ru.osll.goodtravel.models;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by denis on 11/26/16.
 */

public class CategoryOfService extends RealmObject {

    @PrimaryKey
    private long id;

    private String name;
    private String strImg;

    public String getStrImg() {
        if (strImg.isEmpty())
            // TODO: 11/26/16 Сделать вставку дефолтной картинки
            return "defaultimg";
        return strImg;
    }

    public CategoryOfService(){

    }

    public CategoryOfService(String name, String strImg) {
        this.name = name;
        this.strImg = strImg;
    }

    public CategoryOfService(String name) {
        this.name = name;
    }

    public void setStrImg(String strImg) {
        this.strImg = strImg;
    }

    public String getName() {
        return name;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CategoryOfService getByPrimaryKey(Realm realm, int id) {
        return realm.where(CategoryOfService.class).equalTo("id", id).findFirst();
    }

    public static CategoryOfService getByName(Realm realm, String name) {
        return realm.where(CategoryOfService.class).equalTo("id", name).findFirst();
    }
}
