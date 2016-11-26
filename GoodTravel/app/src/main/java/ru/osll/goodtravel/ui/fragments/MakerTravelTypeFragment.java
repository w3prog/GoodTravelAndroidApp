package ru.osll.goodtravel.ui.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CheckableImageButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.osll.goodtravel.R;
import ru.osll.goodtravel.adapters.CategoryAdapter;
import ru.osll.goodtravel.bundles.RouteMakerInfoBundle;
import ru.osll.goodtravel.enums.TravelType;
import ru.osll.goodtravel.models.CategoryOfService;
import ru.osll.goodtravel.utils.DBHelper;

/**
 * Created by artem96 on 10.10.16.
 */

public class MakerTravelTypeFragment extends Fragment {

    private RouteMakerInfoBundle routeInfo;
    private DBHelper dbHelper;

    private RecyclerView categoryRecyclerView;

    public MakerTravelTypeFragment(RouteMakerInfoBundle routeInfo) {

        this.routeInfo = routeInfo;

        if (routeInfo == null) {
            Log.e("RouteMaker Step1: ", "ERROR: RouteInfoBundle is missing");
        }
    }

    public static MakerTravelTypeFragment createInstance(RouteMakerInfoBundle routeInfo) {
        MakerTravelTypeFragment fragment = new MakerTravelTypeFragment(routeInfo);

        // here we can add some information with bundle class

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
        CategoryAdapter categoryAdapter = new CategoryAdapter(CategoryOfService.getAllCategoryOfService(DBHelper.getInstance()));
        categoryAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                View v = view.findViewById(R.id.tickLinearLayout);

                if(v.getVisibility() == View.INVISIBLE)
                {
                    v.setVisibility(View.VISIBLE);
                }
                else
                {
                    v.setVisibility(View.INVISIBLE);
                }
            }
        });
        categoryRecyclerView.setAdapter(categoryAdapter);
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
