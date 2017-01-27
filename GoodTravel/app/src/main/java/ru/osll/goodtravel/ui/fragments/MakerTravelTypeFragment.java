package ru.osll.goodtravel.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.adapters.CategoryAdapter;
import ru.osll.goodtravel.bundles.RouteMakerInfoBundle;
import ru.osll.goodtravel.models.CategoryOfService;
import ru.osll.goodtravel.ui.activities.RouteMakerActivity;
import ru.osll.goodtravel.utils.DBHelper;

/**
 * Created by artem96 on 10.10.16.
 */

public class MakerTravelTypeFragment extends BaseFragment
{

    private RouteMakerInfoBundle routeInfo;
    private DBHelper dbHelper;

    private RecyclerView categoryRecyclerView;

    public MakerTravelTypeFragment() {
    }

    public MakerTravelTypeFragment(RouteMakerInfoBundle routeInfo) {

        this.routeInfo = routeInfo;

        if (routeInfo == null) {
            Log.e("RouteMaker Step1: ", "ERROR: RouteInfoBundle is missing");
        }
    }

    public static MakerTravelTypeFragment createInstance(RouteMakerInfoBundle routeInfo) {
        MakerTravelTypeFragment fragment = new MakerTravelTypeFragment(routeInfo);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.maker_travel_type_fragment, container, false);

        init(v);

        return v;
    }

    private void init(View view)
    {
        categoryRecyclerView = (RecyclerView)view.findViewById(R.id.categoryRecyclerView);

        initCategory();
    }

    private void initCategory()
    {
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        DBHelper.generateData();
        final List<CategoryOfService> categoryOfServiceList = CategoryOfService.getAllCategoryOfService(DBHelper.getInstance());
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryOfServiceList);
        categoryAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                View v = view.findViewById(R.id.tickImageView);

                if(v.getVisibility() == View.INVISIBLE)
                {
                    v.setVisibility(View.VISIBLE);
                }
                else
                {
                    v.setVisibility(View.INVISIBLE);
                }

                CategoryOfService categoryOfService = categoryOfServiceList.get(i);

                if(RouteMakerActivity.categoryOfServiceList.contains(categoryOfService))
                {
                    RouteMakerActivity.categoryOfServiceList.remove(categoryOfService);
                }
                else
                {
                    RouteMakerActivity.categoryOfServiceList.add(categoryOfService);
                }
            }
        });
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void request()
    {
        RouteMakerActivity.categoryOfServiceList.clear();
    }

    /**
     *     private class OnTypeButtonClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
    v.setBackgroundColor(Color.BLUE);

    if (v.getId() == R.id.culture_type_button && !isCultureButtonPressed) {
    routeInfo.addTripType(TravelType.CULTURE);
    isCultureButtonPressed = true;
    } else if (v.getId() == R.id.active_type_button && !isActiveButtonPressed) {
    routeInfo.addTripType(TravelType.ACTIVE);
    isActiveButtonPressed = true;
    } else if (v.getId() == R.id.relax_type_button && !isPassiveButtonPressed) {
    routeInfo.addTripType(TravelType.PASSIVE);
    isPassiveButtonPressed = true;
    }
    }
    }

     */
}
