package ru.osll.goodtravel.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.exceptions.RealmMigrationNeededException;
import ru.osll.goodtravel.enums.PlaceTypeEnum;
import ru.osll.goodtravel.enums.TypeOfGroupEnum;
import ru.osll.goodtravel.models.Address;
import ru.osll.goodtravel.models.CategoryOfService;
import ru.osll.goodtravel.models.Place;
import ru.osll.goodtravel.models.Service;
import ru.osll.goodtravel.models.User;

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
        String[] categories = new String[]
                {
                        "Музей",
                        "Экскурсии по городу",
                        "Спортиные мероприятия",
                        "Концерты",
                        "Кинотеатры",
                        "Театры",
                        "Памятники культуры",
                        "Особые"
                };
        List<CategoryOfService> categoryOfServiceList = new ArrayList<>();
        for (String item : categories) categoryOfServiceList.add(new CategoryOfService(item, categoryOfServiceList.size()));
        realm.copyToRealmOrUpdate(categoryOfServiceList);
        realm.commitTransaction();
    }

    public static void generateData(){

        if(dbIsEmpty()){
            generateUser();
            generateCategory();
            generateAddresses();
            generatePlaces();
            generateService();
            generateDays();
            generatePlans();
        }
    }

    private static void generateUser() {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(new User("user@mail.ru", 1));
        realm.commitTransaction();
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
