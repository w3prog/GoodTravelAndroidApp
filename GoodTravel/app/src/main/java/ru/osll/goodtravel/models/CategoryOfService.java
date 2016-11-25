package ru.osll.goodtravel.models;

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

    public void setName(String name) {
        this.name = name;
    }
}
