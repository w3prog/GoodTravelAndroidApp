package ru.osll.goodtravel.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
    private View singleButton;
    private View coupleButton;
    private View familyButton;

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

        View v = inflater.inflate(R.layout.maker_travel_specifications_fragment, container, false);

        familyButton = v.findViewById(R.id.family_button);
        coupleButton = v.findViewById(R.id.couple_button);
        singleButton = v.findViewById(R.id.single_button);

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
            View tickView = ((FrameLayout)v).getChildAt(1);
            clearSelect((LinearLayout)v.getParent());
            tickView.setVisibility(View.VISIBLE);

            if (v.getId() == R.id.family_button) {
                routeInfo.setPartnerType(PartnerType.FAMILY);
            } else if (v.getId() == R.id.couple_button) {
                routeInfo.setPartnerType(PartnerType.COUPLE);
            } else {
                routeInfo.setPartnerType(PartnerType.SINGLE);
            }
        }

        private void clearSelect(LinearLayout parent)
        {
            for(int i = 0; i < parent.getChildCount(); i++)
            {
                FrameLayout frameLayout = (FrameLayout) parent.getChildAt(i);
                frameLayout.getChildAt(1).setVisibility(View.GONE);
            }
        }
    }

}
