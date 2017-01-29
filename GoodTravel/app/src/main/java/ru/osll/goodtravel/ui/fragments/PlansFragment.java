package ru.osll.goodtravel.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.adapters.PlanAdapter;
import ru.osll.goodtravel.models.DAO.Plan;


public class PlansFragment extends Fragment {

    ArrayList<Plan> plans;
    private RecyclerView recyclerView;
    public static PlansFragment newInstance() {
        PlansFragment fragment = new PlansFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plans_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.route_maker_list);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new PlanAdapter(plans));
        return view;
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
    }
}
