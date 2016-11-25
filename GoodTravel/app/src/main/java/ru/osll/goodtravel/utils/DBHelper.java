package ru.osll.goodtravel.utils;

import io.realm.Realm;
import ru.osll.goodtravel.models.Service;

/**
 * Created by denis on 11/26/16.
 */

public class DBHelper {

    static void generateFakeService(){
        Service service = new Service();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealm(service);
        realm.commitTransaction();
    }
}
