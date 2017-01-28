package ru.osll.goodtravel.models.DAO;

import java.util.ArrayList;

import ru.osll.goodtravel.utils.ModelUtils;


public class Plan {
    private long id;
    private String name;
    private long money;
    private ArrayList<Day> days;

    public Plan(long id, String name, long money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

    public Plan(String name, ArrayList<Day> days) {
        this.name = name;
        this.days=days;
        this.money = ModelUtils.costPrice(days);
    }
    public Plan(){
        name = "unknown plan";
    }

}
