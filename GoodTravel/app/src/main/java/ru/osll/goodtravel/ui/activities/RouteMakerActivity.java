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
import ru.osll.goodtravel.enums.PartnerType;
import ru.osll.goodtravel.models.DAO.Day;
import ru.osll.goodtravel.models.DAO.Place;
import ru.osll.goodtravel.models.DAO.PlaceCategory;
import ru.osll.goodtravel.models.DAO.Plan;
import ru.osll.goodtravel.models.DataBase;
import ru.osll.goodtravel.ui.fragments.BaseFragment;
import ru.osll.goodtravel.ui.fragments.TravelMaker.SelectPlaceFragment;
import ru.osll.goodtravel.ui.fragments.TravelMaker.CalendarFragment;
import ru.osll.goodtravel.ui.fragments.TravelMaker.MakeDayFragment;
import ru.osll.goodtravel.ui.fragments.TravelMaker.MoneyFragment;
import ru.osll.goodtravel.ui.fragments.TravelMaker.PlaceCategoryFragment;

import java.util.ArrayList;
import java.util.Date;

public class RouteMakerActivity extends AppCompatActivity
        implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    private static final String POSITION_ARGUMENT = "posarg";

    public static final int STEP_COUNT = 5;

    private ViewPager pager;
    private RouteMakerAdapter adapter;


    public static ArrayList<PlaceCategory> placeCategoryList = new ArrayList<>();
    public static ArrayList<Place> Places = new ArrayList<>();
    public static ArrayList<Day> Days = new ArrayList<>();
    public static Date SelectedDate;
    public static int progress = 0;
    public static PartnerType partnerType = PartnerType.SINGLE;
    public Plan currentPlan;

    TabHost mTabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.route_maker_activity);
        initViewPager();
        initTabHost();
        currentPlan=new Plan("Новый план");

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
        mTabHost.addTab(mTabHost.newTabSpec("first_step")
                .setIndicator("Шаг 1").setContent(fakeFactory));
        mTabHost.addTab(mTabHost.newTabSpec("second_step")
                .setIndicator("Шаг 2").setContent(fakeFactory));
        mTabHost.addTab(mTabHost.newTabSpec("third_step")
                .setIndicator("Шаг 3").setContent(fakeFactory));
        mTabHost.addTab(mTabHost.newTabSpec("fourth_step")
                .setIndicator("Шаг 4").setContent(fakeFactory));
        mTabHost.addTab(mTabHost.newTabSpec("fifth_step")
                .setIndicator("Шаг 5").setContent(fakeFactory));

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
                    stepFragment = PlaceCategoryFragment.createInstance();
                    break;
                case 1:
                    stepFragment = CalendarFragment.createInstance();
                    break;
                case 2:
                    stepFragment = MoneyFragment.createInstance();
                    break;
                case 3:
                    stepFragment = SelectPlaceFragment.createInstance(RouteMakerActivity.this);
                    break;
                case 4:
                    stepFragment = MakeDayFragment.createInstance( RouteMakerActivity.this);
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

//    private void createFakeTravelPlaces() {
//
//        //String name, String description, String address, int coordinates,
//        //ArrayList<TravelType> type, WealthType wealthType, int averageBill, boolean[] partnerTypeFilter, int pictureURL
//
//        fakePlaces = new ArrayList<>();
//
//        ArrayList<TravelType> travelTypes = new ArrayList<>();
//        travelTypes.add(TravelType.CULTURE);
//        boolean[] partnerTypeFilter = {true,true,true};
//
//        TravelPlace newTravelPlace = new TravelPlace(
//                "Петропавловская крепость",
//                "Крепость была заложена 16 (27) мая 1703 года по совместному плану Петра I" +
//                        " и французского инженера Ламбера: 6 бастионов, соединённых куртинами," +
//                        " 2 равелина, кронверк (первоначально дерево-земляные, в 1730-е-1740-е и " +
//                        "1780-е годы одеты камнем). В 1703 году Заячий остров был соединён с" +
//                        " Петроградской стороной Иоанновским мостом.",
//                "Россия Санкт-Петербург, Заячий Остров",
//                55, travelTypes, WealthType.BUDGET, 100, partnerTypeFilter, R.mipmap.picture1);
//
//        fakePlaces.add(newTravelPlace);
//
//        travelTypes = new ArrayList<>();
//        travelTypes.add(TravelType.ACTIVE);
//
//
//        newTravelPlace = new TravelPlace(
//                "Аквапарк ПитерЛэнд",
//                "Памятка для посетителя\n" +
//                        "\n" +
//                        "При посещении аквапарка с собой необходимо взять купальный костюм, полотенце, обувь на нескользящей резиновой подошве. Полотенце можно взять в прокат в самом аквапарке - 100 р. Также все это можно приобрести у нас в магазине при аквапарке!\n" +
//                        "\n" +
//                        "Для маленьких детей необходимо взять непромокаемые памперсы. Их можно приобрести у нас в магазине.",
//                "Приморский пр., 72 ",
//                55, travelTypes, WealthType.NORMAL, 2000, partnerTypeFilter, R.mipmap.picture2);
//
//        fakePlaces.add(newTravelPlace);
//
//        travelTypes.add(TravelType.CULTURE);
//        partnerTypeFilter[0] = true;
//        partnerTypeFilter[1] = false;
//        partnerTypeFilter[2] = false;
//
//        newTravelPlace = new TravelPlace(
//                "Клуб ИОНОТЕКА",
//                "В клубе регулярно проводятся концерты различных жанров музыки. Можно отдохнуть с" +
//                        " друзьями. Для посетителей работает бар, в котором можно заказать как" +
//                        " алкогольные, так и безалкогольные напитки.",
//                "Большой Концертный Зал «ИОНОТЕКА», Лиговский 50 корпус 12, Saint Petersburg",
//                55, travelTypes, WealthType.BUDGET, 200, partnerTypeFilter, R.mipmap.picture3);
//
//        travelTypes = new ArrayList<>();
//
//        fakePlaces.add(newTravelPlace);
//
//        travelTypes.add(TravelType.CULTURE);
//        partnerTypeFilter[0] = true;
//        partnerTypeFilter[1] = false;
//        partnerTypeFilter[2] = true;
//
//        newTravelPlace = new TravelPlace(
//                "Кунсткамера",
//                "Музей антропологии и этнографии им. Петра Великого (Кунсткамера)" +
//                        " Российской академии наук (МАЭ РАН) – один из крупнейших " +
//                        "и старейших этнографических музеев мира, коллекционные" +
//                        " фонды которого насчитывают свыше 1.2 млн. единиц хранения." +
//                        " Он является преемником первого российского государственного " +
//                        "публичного музея, знаменитой Петровской Кунсткамеры, основанной " +
//                        "Петром I в 1714 г.",
//                "Университетская наб., 3, Санкт-Петербург, 199034",
//                55, travelTypes, WealthType.BUDGET, 200, partnerTypeFilter, R.mipmap.picture4);
//
//        fakePlaces.add(newTravelPlace);
//
//        travelTypes.add(TravelType.ACTIVE);
//        travelTypes.add(TravelType.PASSIVE);
//
//        newTravelPlace = new TravelPlace(
//                "Отель Гельвеция",
//                " Этот отель расположен в 5 минутах ходьбы от Невского " +
//                        "проспекта и станции метро «Маяковская». Неподалеку находится" +
//                        " Московский вокзал, музей Достоевского и различные кафе.\n" +
//                        "\n" +
//                        "Современные номера отеля «Гельвеция» оснащены телевизором с" +
//                        " плоским экраном и мини-баром и обставлены удобной мебелью.",
//                " Этот отель расположен в 5 минутах ходьбы от Невского проспекта и станции метро " +
//                        "«Маяковская». Неподалеку находится Московский вокзал, музей Достоевского и " +
//                        "различные кафе.\n" +
//                        "\n" +
//                        "Современные номера отеля «Гельвеция» оснащены телевизором с плоским экраном и мини-баром и обставлены удобной мебелью.",
//                55, travelTypes, WealthType.LUXURY, 6000, partnerTypeFilter, R.mipmap.picture5);
//
//        fakePlaces.add(newTravelPlace);
//
//    }

    @Override
    public void onTabChanged(String tabId) {
        int currentTab = mTabHost.getCurrentTab();
        pager.setCurrentItem(currentTab);

    }

    @Override
    public void onPageSelected(int selectedItem) {
        mTabHost.setCurrentTab(selectedItem);
        Fragment currentFragment = getSupportFragmentManager()
                .getFragments().get(pager.getCurrentItem());
        ((BaseFragment)currentFragment).request();

    }

    @Override
    public void onPageScrollStateChanged(int something) {

    }

    @Override
    public void onPageScrolled(int some, float unknown, int variables) {

    }

    public void onClickNextButton(View view)
    {
        CalendarFragment fragment =
                (CalendarFragment)adapter.instantiateItem(pager, 1);

        if(pager.getCurrentItem() != mTabHost.getTabWidget().getTabCount() - 1)
        {
            pager.setCurrentItem(pager.getCurrentItem() + 1);
            // TODO: 29.01.17 Реализовать
            //routeInfo.addDay(fragment.getCurrentDay());
        }
        else
        {
            makeDay();
            pager.setCurrentItem(0);
            fragment.fixCurrentDay();
        }
    }

    private void makeDay() {
        //// TODO: 29.01.17 Найти лучший способ копирования даты
        Day day = new Day(this.currentPlan,new Date(SelectedDate.toString()));
        day.setPlaces(Places);
        Places = new ArrayList<>();
        Days.add(day);
    }

    public void onClickFinishButton(View view)
    {
        makeDay();
        CalendarFragment fragment = (CalendarFragment)adapter
                .instantiateItem(pager, 1);
        fragment.fixCurrentDay();

        currentPlan = DataBase.PlanRepository.save(currentPlan);
        DataBase.DayRepository.save(Days);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        if(pager.getCurrentItem() == 0)
        {
            CalendarFragment fragment = (CalendarFragment)adapter
                    .instantiateItem(pager, 1);
            fragment.clearFixed();

            super.onBackPressed();
        }
        else
        {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }
}
