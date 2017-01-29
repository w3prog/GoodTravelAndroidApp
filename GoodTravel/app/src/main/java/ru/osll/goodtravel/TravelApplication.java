package ru.osll.goodtravel;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.vk.sdk.VKSdk;

import ru.osll.goodtravel.models.DataBase;
import ru.osll.goodtravel.utils.DBHelper;

/**
 * Класс для задания параметров запуска приложения.
 * Подключение Мультидекса
 * Подключение и генерации данных в базе данных
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
        //Создание базы данных
        DataBase.getInstance(base);
        Boolean debug = true;
        if (debug)
            DBHelper.generateData();
    }

}
