package ru.osll.goodtravel.utils;

import java.util.ArrayList;
import java.sql.Date;

import ru.osll.goodtravel.enums.TypeOfGroupEnum;
import ru.osll.goodtravel.models.DAO.Day;
import ru.osll.goodtravel.models.DAO.PlaceCategory;
import ru.osll.goodtravel.models.DAO.Place;
import ru.osll.goodtravel.models.DAO.Plan;

public final class DBHelper {

    public static void generateData(){

        ArrayList<PlaceCategory> placeCategoryList = new ArrayList<>();

        placeCategoryList.add(new PlaceCategory("Музей",
                "http://moodle.presby.edu/file.php/1/library.png"));
        placeCategoryList.add(new PlaceCategory("Экскурсии по городу",
                "http://www.webviki.ru/etc/sslimage.php?security=f8cce1334aaae63ce7f0ac3ef378006c&url=http%3A%2F%2Fru.gravatar.com%2Fuserimage%2F12749128%2Fa1bd70d2e349f482d2f32591ec0e2f69.jpg"));
        placeCategoryList.add(new PlaceCategory("Спортивные мероприятия",
                "http://yasenevo.mos.ru/upload/resize_cache/iblock/2dc/80_80_2/depositphotos_30042605_set_of_winter_sport_icons.jpg"));
        placeCategoryList.add(new PlaceCategory("Концерты",
                "https://0.s3.envato.com/files/74612636/Rock.jpg"));
        placeCategoryList.add(new PlaceCategory("Кинотеатры",
                "http://www.stclassifieds.sg/images/ads/hobbies-interests/2014-07-05/music-movies_3681360_102304_ga0_t.jpg"));
        placeCategoryList.add(new PlaceCategory("Театры",
                "http://skydome.ee/wp-content/uploads/2015/02/Skydome_serv1.jpg"));
        placeCategoryList.add(new PlaceCategory("Памятники культуры",
                "http://topbestseller.ru/crontab/2015/imgnews/minkulturi-blagodarit-molodchikov-kotorie-snosyat-pamyatniki-leninu.jpg"));
        placeCategoryList.add(new PlaceCategory("Особые",
                "https://ssl-proxy.my-addr.org/myaddrproxy.php/http/www.avanta-med.ru/images/eye.gif"));

        String place = "Санкт-Петербург";
        PlaceCategory c1 = PlaceCategory.getByName("Музей");
        PlaceCategory c2 = PlaceCategory.getByName("Экскурсии по городу");
        PlaceCategory c3 = PlaceCategory.getByName("Спортиные мероприятия");
        PlaceCategory c4 = PlaceCategory.getByName("Концерты");
        PlaceCategory c5 = PlaceCategory.getByName("Кинотеатры");
        PlaceCategory c6 = PlaceCategory.getByName("Театры");
        PlaceCategory c7 = PlaceCategory.getByName("Памятники культуры");
        PlaceCategory c8 = PlaceCategory.getByName("Особые");
        ArrayList<Place> places = new ArrayList<>();

        places.add(new Place("Русский музей",340,place,"59.991078,30.318714",c1));
        places.add(new Place("Эрмитаж",400,place,"59.992078,30.318435",c1));
        places.add(new Place("Музей артиллерии",1200,place,"59.992534,30.318643",c1));
        places.add(new Place("Музей радиосвязи",600,place,"59.992643,30.318543",c1));
        places.add(new Place("Кунскамера",700,place,"59.992548,30.318643",c1, TypeOfGroupEnum.NO_FAMILY));
        places.add(new Place("Экскурсия по Санкт-Петербургу",2000,place,"59.992564,30.318543",c2));
        places.add(new Place("Водная экскурсия по Санкт-Петербургу",2200,place,"59.992543,30.318543",c2));
        places.add(new Place("Экскурсия по Петергофу",450,place,"59.992436,30.318543",c2));
        places.add(new Place("Экскурсия по городу Пушкину",700,place,"59.992546,30.318643",c2));
        places.add(new Place("Экскурсия по Кромштату",800,place,"59.992345,30.318543",c2));

        places.add(new Place("Хоккей",3000,place,"59.992235,30.318345",c3));
        places.add(new Place("Футбол",2000,place,"59.992346,30.318354",c3));
        places.add(new Place("Чемпионат по Dota 2",200,place,"59.992123,30.318543",c3, TypeOfGroupEnum.NO_FAMILY));
        places.add(new Place("Чемпионат по стрельбе",1000,place,"59.992356,30.318654",c3, TypeOfGroupEnum.ONLY_SINGLE));
        places.add(new Place("Любительский бокс",600,place,"59.992464,30.318356",c3, TypeOfGroupEnum.ONLY_SINGLE));
        places.add(new Place("Рок концерт",1000,place,"59.992456,30.318346",c4));
        places.add(new Place("Поп концерт",500,place,"59.992345,30.318456",c4));
        places.add(new Place("Джаз фестиваль",300,place,"59.992345,30.318346",c4));
        places.add(new Place("Рубий глаз",300,place,"59.992234,30.318346",c5));
        places.add(new Place("Стрелка",400,place,"59.992254,30.318462",c5));
        places.add(new Place("Космос",500,place,"59.992234,30.318234",c5));
        places.add(new Place("Мариинка",2000,place,"59.992345,30.318654",c6));
        places.add(new Place("Music holl",1000,place,"59.992654,30.318234",c6));
        places.add(new Place("Детский театр",125,place,"59.992325,30.318245",c6));
        places.add(new Place("Крейсер Автора",0,place,"59.992235,30.318645",c7));
        places.add(new Place("Петропавловская крепость",0,place,"59.992574,30.318253",c7));
        places.add(new Place("Центральная площадь",0,place,"59.992235,30.31543",c7));
        places.add(new Place("Зимний дворец",0,place,"59.992356,30.31345",c7));
        places.add(new Place("Медный всадник",0,place,"59.990078,30.318120",c7));
        places.add(new Place("Летний марафон по городу",0,place,"59.992000,30.318000",c8));
        places.add(new Place("Сьезд любителей Гарри Поттера",200,place,"59.982076,30.320604",c8));

        Day day1 = new Day();
        ArrayList<Place> places1 = new ArrayList<>();
        places1.add(Place.getByName("Футбол"));
        places1.add(Place.getByName("Детский театр"));
        places1.add(Place.getByName("Крейсер Автора"));
        places1.add(Place.getByName("Водная экскурсия по Санкт-Петербургу"));
        places1.add(Place.getByName("Экскурсия по Кромштату"));
        day1.setPlaces(places1);
        day1.setDate(new Date(117,6,21));

        Day day2 = new Day();
        places1 = new ArrayList<>();
        places1.add(Place.getByName("Футбол"));
        places1.add(Place.getByName("Любительский бокс"));
        places1.add(Place.getByName("Эрмитаж"));
        day2.setPlaces(places1);
        day2.setDate(new Date(117,6,21));

        Day day3 = new Day();
        places1 = new ArrayList<>();
        places1.add(Place.getByName("Космос"));
        places1.add(Place.getByName("Мариинка"));
        places1.add(Place.getByName("Русский музей"));
        day3.setPlaces(places1);
        day3.setDate(new Date(117,6,22));

        Day day4 = new Day();
        places1 = new ArrayList<>();
        places1.add(Place.getByName("Кунскамера"));
        places1.add(Place.getByName("Экскурсия по Санкт-Петербургу"));
        places1.add(Place.getByName("Чемпионат по стрельбе"));
        day4.setPlaces(places1);
        day4.setDate(new Date(117,6,23));

        Day day5 = new Day();
        places1 = new ArrayList<>();
        places1.add(Place.getByName("Музей артиллерии"));
        places1.add(Place.getByName("Экскурсия по Кромштату"));
        places1.add(Place.getByName("Центральная площадь"));
        day5.setPlaces(places1);
        day5.setDate(new Date(117,6,24));

        Day day6 = new Day();
        places1 = new ArrayList<>();
        places1.add(Place.getByName("Music holl"));
        places1.add(Place.getByName("Медный всадник"));
        places1.add(Place.getByName("Сьезд любителей Гарри Поттера"));
        day6.setPlaces(places1);
        day6.setDate(new Date(117,6,24));

        ArrayList<Day> days = new ArrayList<>();
        days.add(day1);
        days.add(day2);
        days.add(day3);
        days.add(day4);
        days.add(day5);
        days.add(day6);

        Plan plan = new Plan("Летний отдых",days);

    }
}
