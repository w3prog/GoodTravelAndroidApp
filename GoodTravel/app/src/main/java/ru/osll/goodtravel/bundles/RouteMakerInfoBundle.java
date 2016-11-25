package ru.osll.goodtravel.bundles;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.osll.goodtravel.enums.PartnerType;
import ru.osll.goodtravel.enums.TravelType;
import ru.osll.goodtravel.enums.WealthType;

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
    private List<CalendarDay> calendarDayList;

    private PartnerType partnerType;
    private WealthType wealthType;

    public RouteMakerInfoBundle() {

        tripTypes = new ArrayList<>();
        partnerType = PartnerType.SINGLE;
        wealthType = WealthType.NORMAL;
        calendarDayList = new ArrayList<>();
    }


    public void addTripType(TravelType type) {
        tripTypes.add(type);
    }

    public ArrayList<TravelType> getTripTypes() {
        return tripTypes;
    }

    public void addDay(CalendarDay day)
    {
        if(!calendarDayList.contains(day) && day != null)
            calendarDayList.add(day);
    }

    public boolean contains(CalendarDay calendarDay)
    {
        return calendarDayList.contains(calendarDay);
    }

    public CalendarDay getFirstDay()
    {
        return calendarDayList.get(0);
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
