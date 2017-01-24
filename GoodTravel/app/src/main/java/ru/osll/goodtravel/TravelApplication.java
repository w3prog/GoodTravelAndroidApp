package ru.osll.goodtravel;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.vk.sdk.VKSdk;

/**
 * Created by artem96 on 06.12.16.
 */

public class TravelApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        VKSdk.initialize(this.getApplicationContext());

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
