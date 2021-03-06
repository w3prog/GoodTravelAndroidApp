package ru.osll.goodtravel.ui.fragments.TravelMaker;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.adapters.CategoryAdapter;
import ru.osll.goodtravel.models.DAO.PlaceCategory;
import ru.osll.goodtravel.models.DataBase;
import ru.osll.goodtravel.ui.activities.RouteMakerActivity;
import ru.osll.goodtravel.ui.fragments.BaseFragment;

/**
 * Фрагмент для выбора категории мастере создания машрутов
 */
public class PlaceCategoryFragment extends BaseFragment
{

    private RecyclerView categoryRecyclerView;


    public static PlaceCategoryFragment createInstance() {
        PlaceCategoryFragment fragment = new PlaceCategoryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.maker_travel_type_fragment, container, false);

        init(v);

        return v;
    }

    private void init(View view) {
        categoryRecyclerView = (RecyclerView)view.findViewById(R.id.categoryRecyclerView);

        initCategory();
    }

    private void initCategory() {
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));

        final List<PlaceCategory> placeCategoryList = DataBase.PlaceCategoryRepository.getAll();
        CategoryAdapter categoryAdapter = new CategoryAdapter(placeCategoryList);
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

                PlaceCategory placeCategory = placeCategoryList.get(i);

                if(RouteMakerActivity.placeCategoryList.contains(placeCategory))
                {
                    RouteMakerActivity.placeCategoryList.remove(placeCategory);
                }
                else
                {
                    RouteMakerActivity.placeCategoryList.add(placeCategory);
                }
            }
        });
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void request()
    {
        RouteMakerActivity.placeCategoryList.clear();
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
