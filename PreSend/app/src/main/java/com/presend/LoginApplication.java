package com.presend;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by artem96 on 04.12.16.
 */

public class LoginApplication extends Application {

    public void onCreate() {
        super.onCreate();

        VKSdk.initialize(this.getApplicationContext());
    }
}
