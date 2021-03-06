package ru.osll.goodtravel.ui.fragments.TravelMaker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.ui.activities.RouteMakerActivity;
import ru.osll.goodtravel.ui.fragments.BaseFragment;


public class CalendarFragment extends BaseFragment {

    private CalendarDay currentDay;
    private MaterialCalendarView calendarView;

    public static CalendarFragment createInstance() {
        CalendarFragment fragment = new CalendarFragment();

        // here we can add some information with bundle class

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.maker_travel_calendar_fragment, container, false);

        calendarView = (MaterialCalendarView) v.findViewById(R.id.route_maker_calendar);
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_REQUEST_MULTIPLE);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public boolean onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                RouteMakerActivity.SelectedDate = date.getDate();
                currentDay = date;

                return false;
            }
        });

        return v;
    }

    public CalendarDay getCurrentDay()
    {
        return currentDay;
    }

    public void fixCurrentDay()
    {
        calendarView.fixDay(currentDay);
    }

    public void clearFixed()
    {
        calendarView.clearFixed();
    }

    @Override
    public void request()
    {

    }
}
