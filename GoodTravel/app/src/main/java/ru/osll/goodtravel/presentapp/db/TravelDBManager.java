package ru.osll.goodtravel.presentapp.db;

/**
 * Класс обертка-синглетон для классов, управляющих
 * отдельными базами данных.
 *
 * Например: Для того, чтобы добавить новое место в базу данных мест
 * необходимо обратиться к этому классу, после чего он выдаст того менеджера,
 * который за управление этой базой данных. В классе PlacesDBManager уже будут
 * непосредственно методы для работы с базой данных мест.
 *
 * Created by Artem Popov on 18.10.16.
 */

public class TravelDBManager {

    private static PlacesDBManager placesManager = null;


    public static PlacesDBManager getPlacesDBManager() {
        if (placesManager == null)
            placesManager = new PlacesDBManager();

        return placesManager;
    }

}
