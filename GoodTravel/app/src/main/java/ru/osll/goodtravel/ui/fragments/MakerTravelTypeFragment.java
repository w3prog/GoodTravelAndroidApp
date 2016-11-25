package ru.osll.goodtravel.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CheckableImageButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.bundles.RouteMakerInfoBundle;
import ru.osll.goodtravel.enums.TravelType;

/**
 * Created by artem96 on 10.10.16.
 */

public class MakerTravelTypeFragment extends Fragment {

    private RouteMakerInfoBundle routeInfo;

    private CheckableImageButton cultureButton;
    private CheckableImageButton activeButton;
    private CheckableImageButton passiveButton;

    private boolean isCultureButtonPressed;
    private boolean isActiveButtonPressed;
    private boolean isPassiveButtonPressed;

    public MakerTravelTypeFragment(RouteMakerInfoBundle routeInfo) {

        this.routeInfo = routeInfo;

        if (routeInfo == null) {
            Log.e("RouteMaker Step1: ", "ERROR: RouteInfoBundle is missing");
        }

        isActiveButtonPressed = isCultureButtonPressed = isPassiveButtonPressed = false;
    }

    public static MakerTravelTypeFragment createInstance(RouteMakerInfoBundle routeInfo) {
        MakerTravelTypeFragment fragment = new MakerTravelTypeFragment(routeInfo);

        // here we can add some information with bundle class

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = (View) inflater.inflate(R.layout.maker_travel_type_fragment, container, false);

        cultureButton = (CheckableImageButton) v.findViewById(R.id.culture_type_button);
        activeButton = (CheckableImageButton) v.findViewById(R.id.active_type_button);
        passiveButton = (CheckableImageButton) v.findViewById(R.id.relax_type_button);

        OnTypeButtonClickListener listener = new OnTypeButtonClickListener();
        cultureButton.setOnClickListener(listener);
        activeButton.setOnClickListener(listener);
        passiveButton.setOnClickListener(listener);

        return v;
    }


    private class OnTypeButtonClickListener implements View.OnClickListener {

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
}
