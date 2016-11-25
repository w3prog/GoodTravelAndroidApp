package ru.osll.goodtravel.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.bundles.RouteMakerInfoBundle;
import ru.osll.goodtravel.enums.TravelType;
import ru.osll.goodtravel.enums.WealthType;
import ru.osll.goodtravel.models.TravelPlace;
import ru.osll.goodtravel.ui.fragments.MakerTravelCalendarFragment;
import ru.osll.goodtravel.ui.fragments.MakerTravelListFragment;
import ru.osll.goodtravel.ui.fragments.MakerTravelPackingFragment;
import ru.osll.goodtravel.ui.fragments.MakerTravelSpecsFragment;
import ru.osll.goodtravel.ui.fragments.MakerTravelTypeFragment;

import java.util.ArrayList;

/**
 * Created by artem96 on 10.10.16.
 */

public class RouteMakerActivity extends AppCompatActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    private static final String POSITION_ARGUMENT = "posarg";

    private RouteMakerInfoBundle routeInfo;
    public static final int STEP_COUNT = 5;

    private ViewPager pager;
    private RouteMakerAdapter adapter;

    private ArrayList<TravelPlace> fakePlaces;

    TabHost mTabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.route_maker_activity);

        initViewPager();

        initTabHost();

        createFakeTravelPlaces();

        routeInfo = new RouteMakerInfoBundle();

    }

    private void initViewPager() {
        pager = (ViewPager) findViewById(R.id.route_maker_container);
        adapter = new RouteMakerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);
    }

    private void initTabHost() {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        FakeTabContent fakeFactory = new FakeTabContent(this);

        mTabHost.addTab(mTabHost.newTabSpec("first_step").setIndicator("Шаг 1").setContent(fakeFactory));
        mTabHost.addTab(mTabHost.newTabSpec("second_step").setIndicator("Шаг 2").setContent(fakeFactory));
        mTabHost.addTab(mTabHost.newTabSpec("third_step").setIndicator("Шаг 3").setContent(fakeFactory));
        mTabHost.addTab(mTabHost.newTabSpec("fourth_step").setIndicator("Шаг 4").setContent(fakeFactory));
        mTabHost.addTab(mTabHost.newTabSpec("fifth_step").setIndicator("Шаг 5").setContent(fakeFactory));

        for(int i = 0; i < mTabHost.getTabWidget().getTabCount(); i++)
            mTabHost.getTabWidget().getChildTabViewAt(i).setEnabled(false);
    }

    private class FakeTabContent implements TabHost.TabContentFactory {

        private Context context;

        public FakeTabContent(Context context) {
            this.context = context;
        }

        public View createTabContent(String tabTag) {

            View fakeView = new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);

            return fakeView;

        }
    }


    private class RouteMakerAdapter extends FragmentPagerAdapter {

        public RouteMakerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment stepFragment = null;

            switch (position) {
                case 0:
                    stepFragment = MakerTravelTypeFragment.createInstance(routeInfo);
                    break;
                case 1:
                    stepFragment = MakerTravelCalendarFragment.createInstance(routeInfo);
                    break;
                case 2:
                    stepFragment = MakerTravelSpecsFragment.createInstance(routeInfo);
                    break;
                case 3:
                    stepFragment = MakerTravelListFragment.createInstance(RouteMakerActivity.this, routeInfo);
                    break;
                case 4:
                    stepFragment = MakerTravelPackingFragment.createInstance(routeInfo, RouteMakerActivity.this);
                    break;
                default:
            }

            return stepFragment;
        }

        @Override
        public int getCount() {
            return STEP_COUNT;
        }


    }

    private void createFakeTravelPlaces() {

        //String name, String description, String address, int coordinates,
        //ArrayList<TravelType> type, WealthType wealthType, int averageBill, boolean[] partnerTypeFilter, int pictureURL

        fakePlaces = new ArrayList<>();

        ArrayList<TravelType> travelTypes = new ArrayList<>();
        travelTypes.add(TravelType.CULTURE);
        boolean[] partnerTypeFilter = {true,true,true};

        TravelPlace newTravelPlace = new TravelPlace(
                "Петропавловская крепость",
                "Крепость была заложена 16 (27) мая 1703 года по совместному плану Петра I" +
                        " и французского инженера Ламбера: 6 бастионов, соединённых куртинами," +
                        " 2 равелина, кронверк (первоначально дерево-земляные, в 1730-е-1740-е и " +
                        "1780-е годы одеты камнем). В 1703 году Заячий остров был соединён с" +
                        " Петроградской стороной Иоанновским мостом.",
                "Россия Санкт-Петербург, Заячий Остров",
                55, travelTypes, WealthType.BUDGET, 100, partnerTypeFilter, R.mipmap.picture1);

        fakePlaces.add(newTravelPlace);

        travelTypes = new ArrayList<>();
        travelTypes.add(TravelType.ACTIVE);


        newTravelPlace = new TravelPlace(
                "Аквапарк ПитерЛэнд",
                "Памятка для посетителя\n" +
                        "\n" +
                        "При посещении аквапарка с собой необходимо взять купальный костюм, полотенце, обувь на нескользящей резиновой подошве. Полотенце можно взять в прокат в самом аквапарке - 100 р. Также все это можно приобрести у нас в магазине при аквапарке!\n" +
                        "\n" +
                        "Для маленьких детей необходимо взять непромокаемые памперсы. Их можно приобрести у нас в магазине.",
                "Приморский пр., 72 ",
                55, travelTypes, WealthType.NORMAL, 2000, partnerTypeFilter, R.mipmap.picture2);

        fakePlaces.add(newTravelPlace);

        travelTypes.add(TravelType.CULTURE);
        partnerTypeFilter[0] = true;
        partnerTypeFilter[1] = false;
        partnerTypeFilter[2] = false;

        newTravelPlace = new TravelPlace(
                "Клуб ИОНОТЕКА",
                "В клубе регулярно проводятся концерты различных жанров музыки. Можно отдохнуть с" +
                        " друзьями. Для посетителей работает бар, в котором можно заказать как" +
                        " алкогольные, так и безалкогольные напитки.",
                "Большой Концертный Зал «ИОНОТЕКА», Лиговский 50 корпус 12, Saint Petersburg",
                55, travelTypes, WealthType.BUDGET, 200, partnerTypeFilter, R.mipmap.picture3);

        travelTypes = new ArrayList<>();

        fakePlaces.add(newTravelPlace);

        travelTypes.add(TravelType.CULTURE);
        partnerTypeFilter[0] = true;
        partnerTypeFilter[1] = false;
        partnerTypeFilter[2] = true;

        newTravelPlace = new TravelPlace(
                "Кунсткамера",
                "Музей антропологии и этнографии им. Петра Великого (Кунсткамера)" +
                        " Российской академии наук (МАЭ РАН) – один из крупнейших " +
                        "и старейших этнографических музеев мира, коллекционные" +
                        " фонды которого насчитывают свыше 1.2 млн. единиц хранения." +
                        " Он является преемником первого российского государственного " +
                        "публичного музея, знаменитой Петровской Кунсткамеры, основанной " +
                        "Петром I в 1714 г.",
                "Университетская наб., 3, Санкт-Петербург, 199034",
                55, travelTypes, WealthType.BUDGET, 200, partnerTypeFilter, R.mipmap.picture4);

        fakePlaces.add(newTravelPlace);

        travelTypes.add(TravelType.ACTIVE);
        travelTypes.add(TravelType.PASSIVE);

        newTravelPlace = new TravelPlace(
                "Отель Гельвеция",
                " Этот отель расположен в 5 минутах ходьбы от Невского " +
                        "проспекта и станции метро «Маяковская». Неподалеку находится" +
                        " Московский вокзал, музей Достоевского и различные кафе.\n" +
                        "\n" +
                        "Современные номера отеля «Гельвеция» оснащены телевизором с" +
                        " плоским экраном и мини-баром и обставлены удобной мебелью.",
                " Этот отель расположен в 5 минутах ходьбы от Невского проспекта и станции метро " +
                        "«Маяковская». Неподалеку находится Московский вокзал, музей Достоевского и " +
                        "различные кафе.\n" +
                        "\n" +
                        "Современные номера отеля «Гельвеция» оснащены телевизором с плоским экраном и мини-баром и обставлены удобной мебелью.",
                55, travelTypes, WealthType.LUXURY, 6000, partnerTypeFilter, R.mipmap.picture5);

        fakePlaces.add(newTravelPlace);

    }

    @Override
    public void onTabChanged(String tabId) {
        int currentTab = mTabHost.getCurrentTab();
        pager.setCurrentItem(currentTab);
    }

    @Override
    public void onPageSelected(int selectedItem) {

        mTabHost.setCurrentTab(selectedItem);

    }

    @Override
    public void onPageScrollStateChanged(int something) {

    }

    @Override
    public void onPageScrolled(int some, float unknown, int variables) {

    }

    public ArrayList<TravelPlace> getFakePlaces() {
        return fakePlaces;
    }

    public void onClickNextButton(View view)
    {
        MakerTravelCalendarFragment fragment = (MakerTravelCalendarFragment)adapter.instantiateItem(pager, 1);
        if(pager.getCurrentItem() != mTabHost.getTabWidget().getTabCount() - 1)
        {
            pager.setCurrentItem(pager.getCurrentItem() + 1);
            routeInfo.addDay(fragment.getCurrentDay());
        }
        else
        {
            pager.setCurrentItem(0);
            fragment.fixCurrentDay();
        }
    }

    @Override
    public void onBackPressed()
    {
        if(pager.getCurrentItem() == 0)
        {
            MakerTravelCalendarFragment fragment = (MakerTravelCalendarFragment)adapter.instantiateItem(pager, 1);
            fragment.clearFixed();

            super.onBackPressed();
        }
        else
        {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }
}