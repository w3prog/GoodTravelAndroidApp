package ru.osll.goodtravel.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.exceptions.RealmMigrationNeededException;
import ru.osll.goodtravel.enums.PlaceTypeEnum;
import ru.osll.goodtravel.enums.TypeOfGroupEnum;
import ru.osll.goodtravel.models.Address;
import ru.osll.goodtravel.models.CategoryOfService;
import ru.osll.goodtravel.models.Place;
import ru.osll.goodtravel.models.Service;

/**
 * Created by denis on 11/26/16.
 */

public class DBHelper {
    private static Realm realm;
    //executor не стал писать

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

    public static Realm getInstance()
    {
        return realm;
    }

    private static void generateService(){

        Place place = Place.getByPrimaryKey(realm,1);
        CategoryOfService c1 = CategoryOfService.getByName(realm,"Музей");
        CategoryOfService c2 = CategoryOfService.getByName(realm,"Экскурсии по городу");
        CategoryOfService c3 = CategoryOfService.getByName(realm,"Спортиные мероприятия");
        CategoryOfService c4 = CategoryOfService.getByName(realm,"Концерты");
        CategoryOfService c5 = CategoryOfService.getByName(realm,"Кинотеатры");
        CategoryOfService c6 = CategoryOfService.getByName(realm,"Театры");
        CategoryOfService c7 = CategoryOfService.getByName(realm,"Памятники культуры");
        CategoryOfService c8 = CategoryOfService.getByName(realm,"Особые");
        RealmList<Service> services = new RealmList<>();
        services.add(new Service("Русский музей",340,place,c1, 0));
        services.add(new Service("Эрмитаж",400,place,c1, 1));
        services.add(new Service("Музей артиллерии",1200,place,c1, 2));
        services.add(new Service("Музей радиосвязи",600,place,c1, 3));
        services.add(new Service("Кунскамера",700,place,c1, TypeOfGroupEnum.NO_FAMILY, 4));
        services.add(new Service("Экскурсия по Санкт-Петербургу",2000,place,c2, 4));
        services.add(new Service("Водная экскурсия по Санкт-Петербургу",2200,place,c2, 6));
        services.add(new Service("Экскурсия по Петергофу",450,place,c2, 7));
        services.add(new Service("Экскурсия по городу Пушкину",700,place,c2, 8));
        services.add(new Service("Экскурсия по Кромштату",800,place,c2,9));
        services.add(new Service("Хоккей",3000,place,c3,10));
        services.add(new Service("Футбол",2000,place,c3,11));
        services.add(new Service("Чемпионат по Dota 2",200,place,c3, TypeOfGroupEnum.NO_FAMILY,11));
        services.add(new Service("Чемпионат по стрельбе",1000,place,c3, TypeOfGroupEnum.ONLY_SINGLE,12));
        services.add(new Service("Любительский бокс",600,place,c3, TypeOfGroupEnum.ONLY_SINGLE,13));
        services.add(new Service("Рок концерт",1000,place,c4,14));
        services.add(new Service("Поп концерт",500,place,c4,15));
        services.add(new Service("Джаз фестиваль",300,place,c4,16));
        services.add(new Service("Рубий глаз",300,place,c5,17));
        services.add(new Service("Стрелка",400,place,c5,18));
        services.add(new Service("Космос",500,place,c5,19));
        services.add(new Service("Мариинка",2000,place,c6,20));
        services.add(new Service("Music holl",1000,place,c6,21));
        services.add(new Service("Детский театр",125,place,c6,22));
        services.add(new Service("Крейсер Автора",0,place,c7,23));
        services.add(new Service("Петропавловская крепость",0,place,c7,24));
        services.add(new Service("Центральная площадь",0,place,c7,25));
        services.add(new Service("Зимний дворец",0,place,c7,26));
        services.add(new Service("Медный всадник",0,place,c7,27));
        services.add(new Service("Летний марафон по городу",0,place,c8,28));
        services.add(new Service("Сьезд любителей Гарри Поттера",200,place,c8,29));


        realm.beginTransaction();
        realm.copyToRealmOrUpdate(services);
        realm.commitTransaction();
    }

    public static void generateCategory(){

        realm.beginTransaction();
        List<CategoryOfService> categoryOfServiceList = new ArrayList<>();
        categoryOfServiceList.add(new CategoryOfService("Музей","http://moodle.presby.edu/file.php/1/library.png"));
        categoryOfServiceList.add(new CategoryOfService("Экскурсии по городу","http://www.webviki.ru/etc/sslimage.php?security=f8cce1334aaae63ce7f0ac3ef378006c&url=http%3A%2F%2Fru.gravatar.com%2Fuserimage%2F12749128%2Fa1bd70d2e349f482d2f32591ec0e2f69.jpg"));
        categoryOfServiceList.add(new CategoryOfService("Спортивные мероприятия","http://yasenevo.mos.ru/upload/resize_cache/iblock/2dc/80_80_2/depositphotos_30042605_set_of_winter_sport_icons.jpg"));
        categoryOfServiceList.add(new CategoryOfService("Концерты","https://0.s3.envato.com/files/74612636/Rock.jpg"));
        categoryOfServiceList.add(new CategoryOfService("Кинотеатры","http://www.stclassifieds.sg/images/ads/hobbies-interests/2014-07-05/music-movies_3681360_102304_ga0_t.jpg"));
        categoryOfServiceList.add(new CategoryOfService("Театры","http://skydome.ee/wp-content/uploads/2015/02/Skydome_serv1.jpg"));
        categoryOfServiceList.add(new CategoryOfService("Памятники культуры","http://topbestseller.ru/crontab/2015/imgnews/minkulturi-blagodarit-molodchikov-kotorie-snosyat-pamyatniki-leninu.jpg"));
        categoryOfServiceList.add(new CategoryOfService("Особые","https://ssl-proxy.my-addr.org/myaddrproxy.php/http/www.avanta-med.ru/images/eye.gif"));

        realm.copyToRealmOrUpdate(categoryOfServiceList);
        realm.commitTransaction();
    }

    public static void generateData(){

        if(dbIsEmpty()){
            generateCategory();
            generateAddresses();
            generatePlaces();
            generateService();
            generateDays();
            generatePlans();
        }
    }


    private static void generatePlans() {
        // TODO: 11/26/16 реализовать метод
    }

    private static void generateDays() {
        // TODO: 11/26/16 реализовать метод
    }

    private static void generatePlaces() {
        realm.beginTransaction();
        Address address = Address.getByPrimaryKey(realm, 1);
        Place fakePlace = new Place();
        fakePlace.setAddress(address);
        fakePlace.setName("Непонятное место");
        fakePlace.setDescription("Какое-то описание");
        fakePlace.setCategory(PlaceTypeEnum.MUSEUM);
        realm.copyToRealmOrUpdate(fakePlace);
        realm.commitTransaction();
    }

    private static void generateAddresses() {
        realm.beginTransaction();
        Address fakeAddress = new Address();
        fakeAddress.setCountry("Россия");
        fakeAddress.setArea("Ленинградская область");
        fakeAddress.setSettlement("Санкт-Петербург");
        fakeAddress.setAddressInSettlements("Улица Пупкина дом 7");
        fakeAddress.setCoordinate("90.60");
        realm.copyToRealmOrUpdate(fakeAddress);
        realm.commitTransaction();
    }

    private static boolean dbIsEmpty() {
        // TODO: 11/26/16 реализовать данный метод
        return true;
    }
}
