package ru.osll.goodtravel;

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

}
