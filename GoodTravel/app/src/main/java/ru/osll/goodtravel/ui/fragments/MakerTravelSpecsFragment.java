package ru.osll.goodtravel.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.enums.PartnerType;

import ru.osll.goodtravel.bundles.RouteMakerInfoBundle;
import ru.osll.goodtravel.enums.WealthType;
import ru.osll.goodtravel.ui.activities.RouteMakerActivity;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by artem96 on 11.10.16.
 */

public class MakerTravelSpecsFragment extends BaseFragment {

    private RouteMakerInfoBundle routeInfo;

    private DiscreteSeekBar seekBar;
    private ImageView singleButton;
    private ImageView coupleButton;
    private ImageView familyButton;

    public MakerTravelSpecsFragment(RouteMakerInfoBundle routeInfo) {

        this.routeInfo = routeInfo;

    }

    public static MakerTravelSpecsFragment createInstance(RouteMakerInfoBundle routeInfo) {
        MakerTravelSpecsFragment fragment = new MakerTravelSpecsFragment(routeInfo);

        // here we can add some information with bundle class

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = (View) inflater.inflate(R.layout.maker_travel_specifications_fragment, container, false);

        familyButton = (ImageView) v.findViewById(R.id.family_button);
        coupleButton = (ImageView) v.findViewById(R.id.couple_button);
        singleButton = (ImageView) v.findViewById(R.id.single_button);

        seekBar = (DiscreteSeekBar) v.findViewById(R.id.seekBar);
        seekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                if(value >= 1500) value = Integer.MAX_VALUE;

                RouteMakerActivity.progress = value;
                if (value == 1) {
                    routeInfo.setWealthType(WealthType.BUDGET);
                } else if (value == 2) {
                    routeInfo.setWealthType(WealthType.NORMAL);
                } else {
                    routeInfo.setWealthType(WealthType.LUXURY);
                }
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        OnClickRelationStatusListener listener = new OnClickRelationStatusListener();

        familyButton.setOnClickListener(listener);
        coupleButton.setOnClickListener(listener);
        singleButton.setOnClickListener(listener);

        return v;
    }

    @Override
    public void request()
    {

    }

    private class OnClickRelationStatusListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.family_button) {
                routeInfo.setPartnerType(PartnerType.FAMILY);
                v.setBackgroundColor(Color.BLUE);
                coupleButton.setBackgroundColor(Color.WHITE);
                singleButton.setBackgroundColor(Color.WHITE);
            } else if (v.getId() == R.id.couple_button) {
                routeInfo.setPartnerType(PartnerType.COUPLE);
                v.setBackgroundColor(Color.BLUE);
                familyButton.setBackgroundColor(Color.WHITE);
                singleButton.setBackgroundColor(Color.WHITE);
            } else {
                routeInfo.setPartnerType(PartnerType.SINGLE);
                v.setBackgroundColor(Color.BLUE);
                coupleButton.setBackgroundColor(Color.WHITE);
                familyButton.setBackgroundColor(Color.WHITE);
            }
        }
    }

}
