package ru.osll.goodtravel.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ru.osll.goodtravel.models.DAO.Place;
import ru.osll.goodtravel.models.DAO.PlaceCategory;

/**
 * Created by denis on 28.01.17.
 */

public class DataBase {

    private Context context;
    private static SQLiteDatabase database;
    private static DataBase sDataBase;
    private static final String TAG = "DataBase.class";
    private static final String ID = "id";

    //Таблица PLACE_CATEGORIES
    private static final String TABLE_PLACE_CATEGORIES = "place_categories";
    private static final String ROW_PLACE_CATEGORIES_NAME = "name";
    private static final String ROW_PLACE_CATEGORIES_IMG = "image";

    //Таблица PLACES
    private static final String TABLE_PLACES = "places";

    private static final String ROW_PLACES_NAME = "name";
    private static final String ROW_PLACES_IMG = "image";
    private static final String ROW_PLACES_DESCRIPTION = "desc";
    private static final String ROW_PLACES_PLACE_NAME = "place_name";

    private static final String ROW_PLACES_ADDRESS = "address";
    private static final String ROW_PLACES_COORDINATE = "coordinate";
    private static final String ROW_PLACES_PRICE = "price";
    private static final String ROW_PLACES_TYPEOFGROUP = "typeOfGroup";
    private static final String ROW_PLACES_PLACE_CATEGORY = "id_category";

    //Таблица DAYS
    private static final String TABLE_DAYS = "days";
    private static final String ROW_DAYS_DATE = "date";
    private static final String ROW_DAYS_PLAN_ID = "id_plan";

    //Таблица DAYS IN PLAN
    private static final String TABLE_PLACE_IN_DAYS = "days_in_plan";
    private static final String ROW_PLACE_IN_DAYS_ID_PLACE = "id_place";
    private static final String ROW_PLACE_IN_DAYS_ID_DAY = "id_day";

    //Таблица PLANS
    private static final String TABLE_PLANS = "plans";
    private static final String ROW_PLANS_NAME = "name";
    private static final String ROW_PLANS_MONEY = "money";

    public static DataBase getInstance(Context context) {
        if (sDataBase==null){
            sDataBase=new DataBase(context);
        }
        return sDataBase;
    }


    private DataBase(Context context){
        this.context = context;
        myDataBaseHelper newDBH = new myDataBaseHelper(context);
        database = newDBH.getWritableDatabase();
    }

    /**
     * Специальный класс для CRUD операций с категориями
     */
    public static class PlaceCategoryRepository {
        public static long save(PlaceCategory pl){
            ContentValues cv = new ContentValues();
            cv.put(ROW_PLACE_CATEGORIES_NAME, pl.getName());
            cv.put(ROW_PLACE_CATEGORIES_IMG, pl.getStrImg());
            return database.insert(TABLE_PLACE_CATEGORIES, null, cv);
        }
        public static PlaceCategory get(long id){
            Cursor c = database.query(TABLE_PLACE_CATEGORIES,
                    null,
                    ID + "=" + id,
                    null, null, null, null);
            PlaceCategory placeCategory;

            if (c.moveToFirst()) {
                placeCategory = new PlaceCategory(
                        c.getString(c.getColumnIndex(ROW_PLACE_CATEGORIES_NAME)),
                        c.getString(c.getColumnIndex(ROW_PLACE_CATEGORIES_IMG))
                );
                return placeCategory;
            } else {
                return null;
            }
        }
        public static ArrayList<PlaceCategory> getAll(){
            ArrayList<PlaceCategory> arrayList = new ArrayList<PlaceCategory>();
            Cursor c = database.rawQuery("Select " + ID + ", " +
                    ROW_PLACE_CATEGORIES_NAME + ", " +
                    ROW_PLACE_CATEGORIES_IMG +" from " + TABLE_PLACE_CATEGORIES + " " +
                    "order by " + ID, null);
            if (c.moveToFirst()) {
                do {
                    arrayList.add(new PlaceCategory(
                            c.getString(c.getColumnIndex(ROW_PLACE_CATEGORIES_NAME)),
                            c.getString(c.getColumnIndex(ROW_PLACE_CATEGORIES_IMG))
                    ));
                    c.moveToNext();
                } while (c.isAfterLast() == false);
            }
            return arrayList;
        }
        public static void update(PlaceCategory pl){
            ContentValues cv = new ContentValues();

            cv.put(ROW_PLACE_CATEGORIES_NAME, pl.getName());
            cv.put(ROW_PLACE_CATEGORIES_IMG, pl.getStrImg());
            database.update(TABLE_PLACE_CATEGORIES, cv, ID + " = ?",
                    new String[]{Long.toString(pl.getId())});
        }
        public static void delete(PlaceCategory pl){
            //// TODO: 28.01.17 не реализованно каскадное удаление
            database.delete(TABLE_PLACE_CATEGORIES, ID + " = " + pl.getId(), null);
        }
    }
    /**
     * Специальный класс для CRUD операций с местами
     */
    public static class PlaceRepository {
        public long save(Place pl){
            ContentValues cv = new ContentValues();
            cv.put(ROW_PLACES_NAME, pl.getName());
            cv.put(ROW_PLACES_IMG, pl.getSrcToImg());
            cv.put(ROW_PLACES_DESCRIPTION, pl.getDescription());
            cv.put(ROW_PLACES_PLACE_NAME, pl.getPlaceName());

            cv.put(ROW_PLACES_ADDRESS, pl.getAddress());
            cv.put(ROW_PLACES_COORDINATE, pl.getCoordinate());
            cv.put(ROW_PLACES_PRICE, pl.getPrice());
            cv.put(ROW_PLACES_TYPEOFGROUP, pl.getTypeOfGroup().toString());
            cv.put(ROW_PLACES_PLACE_CATEGORY, pl.getCategory().getId());

            return database.insert(TABLE_PLACES, null, cv);
        }
        public Place get(long id){
            Cursor c = database.query(TABLE_PLACE_CATEGORIES,
                    null,
                    ID + "=" + id,
                    null, null, null, null);
            Place place;

            if (c.moveToFirst()) {

                PlaceCategory placeCategory = PlaceCategoryRepository
                .get(c.getLong(c.getColumnIndex(ROW_PLACES_PLACE_CATEGORY)));
                place = new Place(
                        c.getLong(c.getColumnIndex(ID)),
                        c.getString(c.getColumnIndex(ROW_PLACES_NAME)),
                        c.getString(c.getColumnIndex(ROW_PLACES_DESCRIPTION)),
                        c.getString(c.getColumnIndex(ROW_PLACES_PLACE_NAME)),
                        c.getString(c.getColumnIndex(ROW_PLACES_ADDRESS)),
                        c.getString(c.getColumnIndex(ROW_PLACES_COORDINATE)),
                        c.getLong(c.getColumnIndex(ROW_PLACES_PRICE)),
                        c.getString(c.getColumnIndex(ROW_PLACES_IMG)),
                        c.getString(c.getColumnIndex(ROW_PLACES_TYPEOFGROUP)),
                        placeCategory);
                return place;
            } else {
                return null;
            }
        }
        public ArrayList<Place> getAll(){
            ArrayList<Place> arrayList = new ArrayList<Place>();
            Cursor c = database.rawQuery("Select " + ID + ", " +
                    ROW_PLACES_NAME + ", " +
                    ROW_PLACES_DESCRIPTION + ", " +
                    ROW_PLACES_PLACE_NAME + ", " +
                    ROW_PLACES_ADDRESS + ", " +
                    ROW_PLACES_COORDINATE + ", " +
                    ROW_PLACES_PRICE + ", " +
                    ROW_PLACES_IMG + ", " +
                    ROW_PLACES_TYPEOFGROUP +" from " + TABLE_PLACES + " " +
                    "order by " + ID, null);
            if (c.moveToFirst()) {
                do {
                    PlaceCategory placeCategory = PlaceCategoryRepository
                            .get(c.getLong(c.getColumnIndex(ROW_PLACES_PLACE_CATEGORY)));
                    arrayList.add(new Place(
                            c.getLong(c.getColumnIndex(ID)),
                            c.getString(c.getColumnIndex(ROW_PLACES_NAME)),
                            c.getString(c.getColumnIndex(ROW_PLACES_DESCRIPTION)),
                            c.getString(c.getColumnIndex(ROW_PLACES_PLACE_NAME)),
                            c.getString(c.getColumnIndex(ROW_PLACES_ADDRESS)),
                            c.getString(c.getColumnIndex(ROW_PLACES_COORDINATE)),
                            c.getLong(c.getColumnIndex(ROW_PLACES_PRICE)),
                            c.getString(c.getColumnIndex(ROW_PLACES_IMG)),
                            c.getString(c.getColumnIndex(ROW_PLACES_TYPEOFGROUP)),
                            placeCategory));
                    c.moveToNext();
                } while (c.isAfterLast() == false);
            }
            return arrayList;
        }
        public  void update(Place pl){
            ContentValues cv = new ContentValues();

            cv.put(ROW_PLACES_NAME, pl.getName());
            cv.put(ROW_PLACES_DESCRIPTION, pl.getDescription());
            cv.put(ROW_PLACES_PLACE_NAME, pl.getPlaceName());
            cv.put(ROW_PLACES_ADDRESS, pl.getAddress());
            cv.put(ROW_PLACES_COORDINATE, pl.getCoordinate());
            cv.put(ROW_PLACES_PRICE, pl.getCoordinate());
            cv.put(ROW_PLACES_IMG, pl.getSrcToImg());
            cv.put(ROW_PLACES_TYPEOFGROUP, pl.getTypeOfGroup().toString());
            cv.put(ROW_PLACES_PLACE_CATEGORY, pl.getCategory().getId());
            database.update(TABLE_PLACE_CATEGORIES, cv, ID + " = ?",
                    new String[]{Long.toString(pl.getId())});
        }
        public  void delete(PlaceCategory pl){
            //// TODO: 28.01.17 не реализованно каскадное удаление
            database.delete(TABLE_PLACES, ID + " = " + pl.getId(), null);
        }
    }



    /**
     * Класс отвечающий за создание базы данных
     */
    private class myDataBaseHelper extends SQLiteOpenHelper{

        private static final String TAG = "DataBase.myDataBaseHelper";
        private static final String DATA_BASE_NAME = "PersonManagersDataBase";
        private static final int DATA_BASE_VERSION = 1;

        public myDataBaseHelper(Context context) {
            super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            createTable(db);
        }

        private void createTable(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_PLACE_CATEGORIES + "("
                    + ID + " integer primary key autoincrement, "
                    + ROW_PLACE_CATEGORIES_NAME + " text, "
                    + ROW_PLACE_CATEGORIES_IMG + " text);");

            db.execSQL("create table " + TABLE_PLACES + "("
                    + ID + " integer primary key autoincrement, "
                    + ROW_PLACES_NAME + " text, "
                    + ROW_PLACES_IMG + " text, "
                    + ROW_PLACES_PLACE_NAME + " text, "
                    + ROW_PLACES_COORDINATE + " text, "
                    + ROW_PLACES_DESCRIPTION + " text, "
                    + ROW_PLACES_ADDRESS + " text, "
                    + ROW_PLACES_PRICE + " text, "
                    + ROW_PLACES_NAME + " text, "
                    + ROW_PLACES_PLACE_CATEGORY + " integer, "
                    + ROW_PLACES_TYPEOFGROUP + " text" + ");");

            db.execSQL("create table " + TABLE_DAYS + "("
                    + ID + " integer primary key autoincrement, "
                    + ROW_DAYS_DATE + " text, "
                    + ROW_DAYS_PLAN_ID + " integer" + ");");

            db.execSQL("create table " + TABLE_PLACE_IN_DAYS + "("
                    + ID + " integer primary key autoincrement, "
                    + ROW_PLACE_IN_DAYS_ID_PLACE + " integer, "
                    + ROW_PLACE_IN_DAYS_ID_DAY + " integer" + ");");

            db.execSQL("create table " + TABLE_PLANS + "("
                    + ID + " integer primary key autoincrement, "
                    + ROW_PLANS_NAME + " text, "
                    + ROW_PLANS_MONEY + " integer" + ");");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //без реализации обновления базы данных
        }
    }
}
