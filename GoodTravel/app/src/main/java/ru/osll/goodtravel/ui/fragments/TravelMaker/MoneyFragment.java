package ru.osll.goodtravel.ui.fragments.TravelMaker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.enums.PartnerType;

import ru.osll.goodtravel.ui.activities.RouteMakerActivity;
import ru.osll.goodtravel.ui.fragments.BaseFragment;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by artem96 on 11.10.16.
 */

public class MoneyFragment extends BaseFragment {

    private DiscreteSeekBar seekBar;
    private View singleButton;
    private View coupleButton;
    private View familyButton;

    public static MoneyFragment createInstance() {
        MoneyFragment fragment = new MoneyFragment();
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
    public void request() {

    }

    private class OnClickRelationStatusListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            View tickView = ((FrameLayout)v).getChildAt(1);
            clearSelect((LinearLayout)v.getParent());
            tickView.setVisibility(View.VISIBLE);

            if (v.getId() == R.id.family_button) {
                RouteMakerActivity.partnerType = PartnerType.FAMILY;
            } else if (v.getId() == R.id.couple_button) {
                RouteMakerActivity.partnerType = PartnerType.COUPLE;
            } else {
                RouteMakerActivity.partnerType = PartnerType.SINGLE;
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
