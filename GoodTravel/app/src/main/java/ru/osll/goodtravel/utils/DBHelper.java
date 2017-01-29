package ru.osll.goodtravel.utils;

import java.util.ArrayList;
import java.sql.Date;

import ru.osll.goodtravel.enums.TypeOfGroupEnum;
import ru.osll.goodtravel.models.DAO.Day;
import ru.osll.goodtravel.models.DAO.PlaceCategory;
import ru.osll.goodtravel.models.DAO.Place;
import ru.osll.goodtravel.models.DAO.Plan;
import ru.osll.goodtravel.models.DataBase;

public final class DBHelper {

    /**
     * Груда китайского кода для генерации начальных данных
     */
    public static void generateData(){

        DataBase.clearData();
        String place = "Санкт-Петербург";
        PlaceCategory c1 = new PlaceCategory("Музей",
                "http://moodle.presby.edu/file.php/1/library.png");
        PlaceCategory c2 = new PlaceCategory("Экскурсии по городу",
                "http://www.webviki.ru/etc/sslimage.php?security=f8cce1334aaae63ce7f0ac3ef378006c&url=http%3A%2F%2Fru.gravatar.com%2Fuserimage%2F12749128%2Fa1bd70d2e349f482d2f32591ec0e2f69.jpg");
        PlaceCategory c3 = new PlaceCategory("Спортивные мероприятия",
                "http://yasenevo.mos.ru/upload/resize_cache/iblock/2dc/80_80_2/depositphotos_30042605_set_of_winter_sport_icons.jpg");
        PlaceCategory c4 = new PlaceCategory("Концерты",
                "https://0.s3.envato.com/files/74612636/Rock.jpg");
        PlaceCategory c5 = new PlaceCategory("Кинотеатры",
                "http://www.stclassifieds.sg/images/ads/hobbies-interests/2014-07-05/music-movies_3681360_102304_ga0_t.jpg");
        PlaceCategory c6 = new PlaceCategory("Театры",
                "http://skydome.ee/wp-content/uploads/2015/02/Skydome_serv1.jpg");
        PlaceCategory c7 = new PlaceCategory("Памятники культуры",
                "http://topbestseller.ru/crontab/2015/imgnews/minkulturi-blagodarit-molodchikov-kotorie-snosyat-pamyatniki-leninu.jpg");
        PlaceCategory c8 = new PlaceCategory("Особые",
                "https://ssl-proxy.my-addr.org/myaddrproxy.php/http/www.avanta-med.ru/images/eye.gif");

        c1 = DataBase.PlaceCategoryRepository.save(c1);
        c2 = DataBase.PlaceCategoryRepository.save(c2);
        c3 = DataBase.PlaceCategoryRepository.save(c3);
        c4 = DataBase.PlaceCategoryRepository.save(c4);
        c5 = DataBase.PlaceCategoryRepository.save(c5);
        c6 = DataBase.PlaceCategoryRepository.save(c6);
        c7 = DataBase.PlaceCategoryRepository.save(c7);
        c8 = DataBase.PlaceCategoryRepository.save(c8);

        Place place1 = new Place("Русский музей",340,place,"59.991078,30.318714",c1);
        Place place2 = new Place("Эрмитаж",400,place,"59.992078,30.318435",c1);
        Place place3 = new Place("Музей артиллерии",1200,place,"59.992534,30.318643",c1);
        Place place4 = new  Place("Музей радиосвязи",600,place,"59.992643,30.318543",c1);
        Place place5 = new Place("Кунскамера",700,place,"59.992548,30.318643",c1, TypeOfGroupEnum.NO_FAMILY);
        Place place6 = new Place("Экскурсия по Санкт-Петербургу",2000,place,"59.992564,30.318543",c2);
        Place place7 = new Place("Водная экскурсия по Санкт-Петербургу",2200,place,"59.992543,30.318543",c2);
        Place place8 = new Place("Экскурсия по Петергофу",450,place,"59.992436,30.318543",c2);
        Place place9 = new Place("Экскурсия по городу Пушкину",700,place,"59.992546,30.318643",c2);
        Place place10 = new Place("Экскурсия по Кромштату",800,place,"59.992345,30.318543",c2);

        Place place11 = new Place("Хоккей",3000,place,"59.992235,30.318345",c3);
        Place place12 = new Place("Футбол",2000,place,"59.992346,30.318354",c3);
        Place place13 = new Place("Чемпионат по Dota 2",200,place,"59.992123,30.318543",c3, TypeOfGroupEnum.NO_FAMILY);
        Place place14 = new Place("Чемпионат по стрельбе",1000,place,"59.992356,30.318654",c3, TypeOfGroupEnum.ONLY_SINGLE);
        Place place15 = new Place("Любительский бокс",600,place,"59.992464,30.318356",c3, TypeOfGroupEnum.ONLY_SINGLE);
        Place place16 = new Place("Рок концерт",1000,place,"59.992456,30.318346",c4);
        Place place17 = new Place("Поп концерт",500,place,"59.992345,30.318456",c4);
        Place place18 = new Place("Джаз фестиваль",300,place,"59.992345,30.318346",c4);
        Place place19 = new Place("Рубий глаз",300,place,"59.992234,30.318346",c5);
        Place place20 = new Place("Стрелка",400,place,"59.992254,30.318462",c5);
        Place place21 = new Place("Космос",500,place,"59.992234,30.318234",c5);
        Place place22 = new Place("Мариинка",2000,place,"59.992345,30.318654",c6);
        Place place23 = new Place("Music holl",1000,place,"59.992654,30.318234",c6);
        Place place24 = new Place("Детский театр",125,place,"59.992325,30.318245",c6);
        Place place25 = new Place("Крейсер Автора",0,place,"59.992235,30.318645",c7);
        Place place26 = new Place("Петропавловская крепость",0,place,"59.992574,30.318253",c7);
        Place place27 = new Place("Центральная площадь",0,place,"59.992235,30.31543",c7);
        Place place28 = new Place("Зимний дворец",0,place,"59.992356,30.31345",c7);
        Place place29 = new Place("Медный всадник",0,place,"59.990078,30.318120",c7);
        Place place30 = new Place("Летний марафон по городу",0,place,"59.992000,30.318000",c8);
        Place place31 = new Place("Сьезд любителей Гарри Поттера",200,place,"59.982076,30.320604",c8);

        place1 = DataBase.PlaceRepository.save(place1);
        place2 = DataBase.PlaceRepository.save(place2);
        place3 = DataBase.PlaceRepository.save(place3);
        place4 = DataBase.PlaceRepository.save(place4);
        place5 = DataBase.PlaceRepository.save(place5);
        place6 = DataBase.PlaceRepository.save(place6);
        place7 = DataBase.PlaceRepository.save(place7);
        place8 = DataBase.PlaceRepository.save(place8);
        place9 = DataBase.PlaceRepository.save(place9);
        place10 = DataBase.PlaceRepository.save(place10);
        place11 = DataBase.PlaceRepository.save(place11);
        place12 = DataBase.PlaceRepository.save(place12);
        place13 = DataBase.PlaceRepository.save(place13);
        place14 = DataBase.PlaceRepository.save(place14);
        place15 = DataBase.PlaceRepository.save(place15);
        place16 = DataBase.PlaceRepository.save(place16);
        place17 = DataBase.PlaceRepository.save(place17);
        place18 = DataBase.PlaceRepository.save(place18);
        place19 = DataBase.PlaceRepository.save(place19);
        place20 = DataBase.PlaceRepository.save(place20);
        place21 = DataBase.PlaceRepository.save(place21);
        place22 = DataBase.PlaceRepository.save(place22);
        place23 = DataBase.PlaceRepository.save(place23);
        place24 = DataBase.PlaceRepository.save(place24);
        place25 = DataBase.PlaceRepository.save(place25);
        place26 = DataBase.PlaceRepository.save(place26);
        place27 = DataBase.PlaceRepository.save(place27);
        place28 = DataBase.PlaceRepository.save(place28);
        place29 = DataBase.PlaceRepository.save(place29);
        place30 = DataBase.PlaceRepository.save(place30);
        place31 = DataBase.PlaceRepository.save(place31);

        Plan plan = new Plan("Летний отдых");
        plan = DataBase.PlanRepository.save(plan);


        Day day1 = new Day();
        ArrayList<Place> places1 = new ArrayList<>();
        places1.add(place1);
        places1.add(place2);
        places1.add(place3);
        places1.add(place4);
        places1.add(place5);
        day1.setPlaces(places1);
        day1.setPlan(plan);
        day1.setDate(new Date(117,6,21));
        day1 = DataBase.DayRepository.save(day1);

        Day day2 = new Day();
        places1 = new ArrayList<>();
        places1.add(place6);
        places1.add(place7);
        places1.add(place8);
        day2.setPlaces(places1);
        day2.setPlan(plan);
        day2.setDate(new Date(117,6,21));
        day2 = DataBase.DayRepository.save(day2);

        Day day3 = new Day();
        places1 = new ArrayList<>();
        places1.add(place9);
        places1.add(place10);
        places1.add(place11);
        day3.setPlaces(places1);
        day3.setPlan(plan);
        day3.setDate(new Date(117,6,22));
        day3 = DataBase.DayRepository.save(day3);

        Day day4 = new Day();
        places1 = new ArrayList<>();
        places1.add(place12);
        places1.add(place13);
        places1.add(place14);
        day4.setPlaces(places1);
        day4.setPlan(plan);
        day4.setDate(new Date(117,6,23));
        day4 = DataBase.DayRepository.save(day4);

        Day day5 = new Day();
        places1 = new ArrayList<>();
        places1.add(place15);
        places1.add(place16);
        places1.add(place17);
        day5.setPlaces(places1);
        day5.setPlan(plan);
        day5.setDate(new Date(117,6,24));
        day5 = DataBase.DayRepository.save(day5);

        Day day6 = new Day();
        places1 = new ArrayList<>();
        places1.add(place17);
        places1.add(place18);
        places1.add(place19);
        day6.setPlaces(places1);
        day6.setPlan(plan);
        day6.setDate(new Date(117,6,24));
        day6 = DataBase.DayRepository.save(day6);

    }
}
