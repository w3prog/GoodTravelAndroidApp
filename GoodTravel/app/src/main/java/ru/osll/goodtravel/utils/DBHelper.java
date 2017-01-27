package ru.osll.goodtravel.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.exceptions.RealmMigrationNeededException;
import ru.osll.goodtravel.enums.TypeOfGroupEnum;
import ru.osll.goodtravel.models.Day;
import ru.osll.goodtravel.models.PlaceCategory;
import ru.osll.goodtravel.models.Place;
import ru.osll.goodtravel.models.Plan;

/**
 * Created by denis on 11/26/16.
 */

public final class DBHelper {
    private static Realm realm;
    //executor не стал писать

    public static void execute(RealmList objects){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(objects);
        realm.commitTransaction();
    }

    public static void initRealm(Context context)
    {
        try
        {
            RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context).build();
            Realm.setDefaultConfiguration(realmConfiguration);
            realm = Realm.getDefaultInstance();
        }
        catch (RealmMigrationNeededException e)
        {
            RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context).deleteRealmIfMigrationNeeded().build();
            Realm.setDefaultConfiguration(realmConfiguration);
            realm = Realm.getDefaultInstance();
        }
    }

    private static boolean dbIsEmpty() {
        // TODO: 11/26/16 реализовать данный метод
        return true;
    }

    public static Realm getInstance()
    {
        return realm;
    }

    public static void generateData(){

        if(dbIsEmpty()){
            generateCategory();
            generatePlaces();
            generatePlans();
        }
    }

    public static void generateCategory(){


        RealmList<PlaceCategory> placeCategoryList = new RealmList<>();
        placeCategoryList.add(new PlaceCategory("Музей","http://moodle.presby.edu/file.php/1/library.png"));
        placeCategoryList.add(new PlaceCategory("Экскурсии по городу","http://www.webviki.ru/etc/sslimage.php?security=f8cce1334aaae63ce7f0ac3ef378006c&url=http%3A%2F%2Fru.gravatar.com%2Fuserimage%2F12749128%2Fa1bd70d2e349f482d2f32591ec0e2f69.jpg"));
        placeCategoryList.add(new PlaceCategory("Спортивные мероприятия","http://yasenevo.mos.ru/upload/resize_cache/iblock/2dc/80_80_2/depositphotos_30042605_set_of_winter_sport_icons.jpg"));
        placeCategoryList.add(new PlaceCategory("Концерты","https://0.s3.envato.com/files/74612636/Rock.jpg"));
        placeCategoryList.add(new PlaceCategory("Кинотеатры","http://www.stclassifieds.sg/images/ads/hobbies-interests/2014-07-05/music-movies_3681360_102304_ga0_t.jpg"));
        placeCategoryList.add(new PlaceCategory("Театры","http://skydome.ee/wp-content/uploads/2015/02/Skydome_serv1.jpg"));
        placeCategoryList.add(new PlaceCategory("Памятники культуры","http://topbestseller.ru/crontab/2015/imgnews/minkulturi-blagodarit-molodchikov-kotorie-snosyat-pamyatniki-leninu.jpg"));
        placeCategoryList.add(new PlaceCategory("Особые","https://ssl-proxy.my-addr.org/myaddrproxy.php/http/www.avanta-med.ru/images/eye.gif"));

        execute(placeCategoryList);
    }

    private static void generatePlaces(){

        String place = "Санкт-Петербург";
        PlaceCategory c1 = PlaceCategory.getByName("Музей");
        PlaceCategory c2 = PlaceCategory.getByName("Экскурсии по городу");
        PlaceCategory c3 = PlaceCategory.getByName("Спортиные мероприятия");
        PlaceCategory c4 = PlaceCategory.getByName("Концерты");
        PlaceCategory c5 = PlaceCategory.getByName("Кинотеатры");
        PlaceCategory c6 = PlaceCategory.getByName("Театры");
        PlaceCategory c7 = PlaceCategory.getByName("Памятники культуры");
        PlaceCategory c8 = PlaceCategory.getByName("Особые");
        RealmList<Place> places = new RealmList<>();
        places.add(new Place("Русский музей",340,place,c1));
        places.add(new Place("Эрмитаж",400,place,c1));
        places.add(new Place("Музей артиллерии",1200,place,c1));
        places.add(new Place("Музей радиосвязи",600,place,c1));
        places.add(new Place("Кунскамера",700,place,c1, TypeOfGroupEnum.NO_FAMILY));
        places.add(new Place("Экскурсия по Санкт-Петербургу",2000,place,c2));
        places.add(new Place("Водная экскурсия по Санкт-Петербургу",2200,place,c2));
        places.add(new Place("Экскурсия по Петергофу",450,place,c2));
        places.add(new Place("Экскурсия по городу Пушкину",700,place,c2));
        places.add(new Place("Экскурсия по Кромштату",800,place,c2));
        places.add(new Place("Хоккей",3000,place,c3));
        places.add(new Place("Футбол",2000,place,c3));
        places.add(new Place("Чемпионат по Dota 2",200,place,c3, TypeOfGroupEnum.NO_FAMILY));
        places.add(new Place("Чемпионат по стрельбе",1000,place,c3, TypeOfGroupEnum.ONLY_SINGLE));
        places.add(new Place("Любительский бокс",600,place,c3, TypeOfGroupEnum.ONLY_SINGLE));
        places.add(new Place("Рок концерт",1000,place,c4));
        places.add(new Place("Поп концерт",500,place,c4));
        places.add(new Place("Джаз фестиваль",300,place,c4));
        places.add(new Place("Рубий глаз",300,place,c5));
        places.add(new Place("Стрелка",400,place,c5));
        places.add(new Place("Космос",500,place,c5));
        places.add(new Place("Мариинка",2000,place,c6));
        places.add(new Place("Music holl",1000,place,c6));
        places.add(new Place("Детский театр",125,place,c6));
        places.add(new Place("Крейсер Автора",0,place,c7));
        places.add(new Place("Петропавловская крепость",0,place,c7));
        places.add(new Place("Центральная площадь",0,place,c7));
        places.add(new Place("Зимний дворец",0,place,c7));
        places.add(new Place("Медный всадник",0,place,c7));
        places.add(new Place("Летний марафон по городу",0,place,c8));
        places.add(new Place("Сьезд любителей Гарри Поттера",200,place,c8));

        execute(places);

    }

    private static void generatePlans() {

        Day day1 = new Day();
        Day day2 = new Day();
        Day day3 = new Day();
        Day day4 = new Day();
        Day day5 = new Day();
        Day day6 = new Day();
        Plan plan = new Plan();
    }

}
