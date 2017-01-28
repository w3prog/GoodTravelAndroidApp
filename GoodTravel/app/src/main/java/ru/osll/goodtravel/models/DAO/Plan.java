package ru.osll.goodtravel.models.DAO;

import java.util.ArrayList;

import ru.osll.goodtravel.utils.ModelUtils;


public class Plan {

    private String name;
    private int money;
    private ArrayList<Day> days;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
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
