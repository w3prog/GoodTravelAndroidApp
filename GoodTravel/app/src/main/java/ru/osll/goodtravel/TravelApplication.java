package ru.osll.goodtravel;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.vk.sdk.VKSdk;

import ru.osll.goodtravel.utils.DBHelper;

/**
 * Created by artem96 on 06.12.16.
 */

public class TravelApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        VKSdk.initialize(this.getApplicationContext());
        //todo определить когда нужно загружать Мультидекс.
        MultiDex.install(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        DBHelper.initRealm(this);
    }

}
