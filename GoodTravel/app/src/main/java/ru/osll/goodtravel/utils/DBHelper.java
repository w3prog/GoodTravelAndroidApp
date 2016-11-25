package ru.osll.goodtravel.utils;

import io.realm.Realm;
import ru.osll.goodtravel.enums.PlaceTypeEnum;
import ru.osll.goodtravel.models.Address;
import ru.osll.goodtravel.models.CategoryOfService;
import ru.osll.goodtravel.models.Place;
import ru.osll.goodtravel.models.Service;
import ru.osll.goodtravel.models.User;

/**
 * Created by denis on 11/26/16.
 */

public class DBHelper {
    final static Realm realm = Realm.getDefaultInstance();
    //executor не стал писать
    private static void generateService(){

        Service service = new Service();
        Place place = Place.getByPrimaryKey(realm,1);

        realm.beginTransaction();

        realm.copyToRealm(service);
        realm.commitTransaction();
    }

    public static void generateCategory(){
        realm.beginTransaction();
        realm.copyToRealm(new CategoryOfService("Музей"));
        realm.copyToRealm(new CategoryOfService("Экскурсии по городу"));
        realm.copyToRealm(new CategoryOfService("Спортиные мероприятия"));
        realm.copyToRealm(new CategoryOfService("Концерты"));
        realm.copyToRealm(new CategoryOfService("Кинотеатры"));
        realm.copyToRealm(new CategoryOfService("Театры"));
        realm.copyToRealm(new CategoryOfService("Памятники культуры"));
        realm.copyToRealm(new CategoryOfService("Особые"));
        realm.commitTransaction();
    }

    static void generateData(){

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
        realm.commitTransaction();
        realm.copyToRealm(new User("user@mail.ru"));
        realm.commitTransaction();
    }

    private static void generatePlans() {
    }

    private static void generateDays() {
    }

    private static void generatePlaces() {
        realm.commitTransaction();
        Address address = Address.getByPrimaryKey(realm,1);
        Place fakePlace = new Place();
        fakePlace.setAddress(address);
        fakePlace.setName("Непонятное место");
        fakePlace.setDescription("Какое-то описание");
        fakePlace.setType(PlaceTypeEnum.MUSEUM);
        realm.copyToRealm(fakePlace);
        realm.commitTransaction();
    }

    private static void generateAddresses() {
        realm.commitTransaction();
        Address fakeAddress = new Address();
        fakeAddress.setCountry("Россия");
        fakeAddress.setArea("Ленинградская область");
        fakeAddress.setSettlement("Санкт-Петербург");
        fakeAddress.setAddressInSettlements("Улица Пупкина дом 7");
        fakeAddress.setCoordinate("90.60");
        realm.copyToRealm(fakeAddress);
        realm.commitTransaction();
    }

    private static boolean dbIsEmpty() {
        // TODO: 11/26/16 реализовать данный метод
        return true;
    }
}
