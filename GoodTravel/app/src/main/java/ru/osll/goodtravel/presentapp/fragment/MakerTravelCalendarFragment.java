package ru.osll.goodtravel.presentapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.presentapp.RouteMakerInfoBundle;

/**
 * Created by artem96 on 11.10.16.
 */
//TODO change nonDefault constructor

public class MakerTravelCalendarFragment extends Fragment {

    private RouteMakerInfoBundle routeInfo;

    private MaterialCalendarView calendarView;

    public MakerTravelCalendarFragment(RouteMakerInfoBundle routeInfo) {
        this.routeInfo = routeInfo;
    }

    public static MakerTravelCalendarFragment createInstance(RouteMakerInfoBundle routeInfo) {
        MakerTravelCalendarFragment fragment = new MakerTravelCalendarFragment(routeInfo);

        // here we can add some information with bundle class

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.maker_travel_calendar_fragment, container, false);

        calendarView = (MaterialCalendarView) v.findViewById(R.id.route_maker_calendar);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                routeInfo.setFirstDay(date);
            }
        });

        return v;
    }

}
