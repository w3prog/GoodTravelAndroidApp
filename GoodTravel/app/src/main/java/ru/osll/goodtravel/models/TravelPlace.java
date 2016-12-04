package ru.osll.goodtravel.models;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import ru.osll.goodtravel.R;
import ru.osll.goodtravel.enums.TravelType;
import ru.osll.goodtravel.enums.WealthType;

/**
 * Любое место (ресторан, отель, торговый центр) представлено
 * этим объектом.
 *
 * Created by artem96 on 01.11.16.
 */

public class TravelPlace {

    private String name;
    private String description;
    private String address;
    private int picture;
    private int coordinates;
    private ArrayList<TravelType> type;
    private int averageBill;

    private WealthType minWealthType;
    private boolean[] partnerTypeFilter = {true, true, true};

    public TravelPlace(String name, String description, ArrayList<TravelType> type, String picture) {

        this.name = name;
        this.description = description;
        this.picture = android.R.drawable.sym_def_app_icon;
        this.type = type;

        address = "Unknown";
        coordinates = -1;
        averageBill = -1;

    }

    public TravelPlace(String name, String description, String address, int coordinates,
                       ArrayList<TravelType> type, WealthType wealthType, int averageBill, boolean[] partnerTypeFilter, int picture) {

        this.name = name;
        this.description = description;
        this.picture = picture;
        this.address = address;
        this.coordinates = coordinates;
        this.type = type;
        this.minWealthType = wealthType;
        this.partnerTypeFilter = partnerTypeFilter;
        this.averageBill = averageBill;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<TravelType> getType() {
        return type;
    }

    public void setType(ArrayList<TravelType> type) {
        this.type = type;
    }

    public WealthType getMinWealthType() {
        return minWealthType;
    }

    public void setMinWealthType(WealthType minWealthType) {
        this.minWealthType = minWealthType;
    }

    public boolean[] getPartnerTypeFilter() {
        return partnerTypeFilter;
    }

    public void setPartnerTypeFilter(boolean[] partnerTypeFilter) {
        this.partnerTypeFilter = partnerTypeFilter;
    }

    public int getAverageBill() {
        return averageBill;
    }

    public void setAverageBill(int bill) {
        this.averageBill = bill;
    }
}
