package ru.osll.goodtravel.presentapp;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Простой Java-bean, содержащий сведения, полученные
 * в ходе построения маршрута в RouteMaker. Каждый шаг
 * RouteMakerа должен передавать дальше эту структуру.
 * После последнего шага этот объект будет содержать всю
 * необходимую информацию для построения и сохранения маршрута.
 *
 * Created by artem96 on 31.10.16.
 */

public class RouteMakerInfoBundle implements Serializable{

    private ArrayList<TravelType> tripTypes;
    private CalendarDay firstDay;
    private CalendarDay secondDay;

    private PartnerType partnerType;
    private WealthType wealthType;

    public RouteMakerInfoBundle() {

        tripTypes = new ArrayList<>();
        partnerType = PartnerType.SINGLE;
        wealthType = WealthType.NORMAL;
    }


    public void addTripType(TravelType type) {
        tripTypes.add(type);
    }

    public ArrayList<TravelType> getTripTypes() {
        return tripTypes;
    }

    public void setFirstDay(CalendarDay firstDay) {
        this.firstDay = firstDay;
    }

    public CalendarDay getFirstDay() {
        return firstDay;
    }

    public void setPartnerType(PartnerType partner) {
        this.partnerType = partner;
    }

    public PartnerType getPartnerType() {
        return partnerType;
    }

    public WealthType getWealthType() {
        return wealthType;
    }

    public void setWealthType(WealthType wealthType) {
        this.wealthType = wealthType;
    }
}
