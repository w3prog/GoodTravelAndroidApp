package ru.osll.goodtravel.models;

import io.realm.RealmObject;

/**
 * Created by denis on 10/30/16.
 */

public class Address extends RealmObject {
    private String country;
    private String area;
    private String coordinate;
    private String settlement;
    private String addressInSettlements;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getAddressInSettlements() {
        return addressInSettlements;
    }

    public void setAddressInSettlements(String addressInSettlements) {
        this.addressInSettlements = addressInSettlements;
    }

    public void setArea(String are) {
        this.area = are;
    }
}
