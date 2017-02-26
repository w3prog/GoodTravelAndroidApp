package ru.osll.goodtravel.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.adapters.CategoryAdapter;
import ru.osll.goodtravel.adapters.DaysInPlanAdapter;
import ru.osll.goodtravel.models.DAO.Day;
import ru.osll.goodtravel.models.DAO.PlaceCategory;
import ru.osll.goodtravel.models.DataBase;
import ru.osll.goodtravel.ui.activities.ContentActivity;

/**
 * Фрагмент для отображения списка дней плане.
 *
 */
public class DaysInPlanListFragment extends Fragment {

    ArrayList<Day> days;
    public static final String TAG = "DaysInPlanListFragment";
    public static final String DAYS_ARG = "DAYS_ARG";

    private RecyclerView daysRecyclerView;

    public DaysInPlanListFragment() {
        // Required empty public constructor
    }


    public static DaysInPlanListFragment newInstance(long planId) {
        DaysInPlanListFragment fragment = new DaysInPlanListFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(DAYS_ARG,planId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_days_in_plan_list, container, false);
        days = DataBase.DayRepository.getDaysFromPlan(this.getArguments().getLong(DAYS_ARG,1));
        daysRecyclerView = (RecyclerView)view.findViewById(R.id.daysRecyclerView);
        DaysInPlanAdapter daysAdapter = new DaysInPlanAdapter(days);
        daysAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GoogleMapFragment fragment = new GoogleMapFragment();
                //заменить вызов
                //GoogleMapFragment(days.get(position).getId());
                ((ContentActivity)getActivity()).setFragment(fragment);
                ;
            }
        });


        daysRecyclerView.setAdapter(daysAdapter);
        return view;
    }

}
