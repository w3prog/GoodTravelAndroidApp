package ru.osll.goodtravel.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by denis on 11/26/16.
 */

public class Service extends RealmObject {
    private String name;
    private String Description;
    private Date startDate;
    private Date finishDate;
    private int price;
    private String srcToImg;

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
        if (srcToImg.isEmpty() || srcToImg == null){
            //todo добавить стандартную картинку при отсудствии картинки.
            return "defaultimage";
        }

        return srcToImg;
    }

    public void setSrcToImg(String srcToImg) {
        this.srcToImg = srcToImg;
    }
}
