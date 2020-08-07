package org.moloshnikov.votingsystem.to;

import org.moloshnikov.votingsystem.model.Dish;
import org.moloshnikov.votingsystem.model.Restaurant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayMenuTo {

    {
        dayMenu = new ArrayList<>();
    }

    private Date date;
    private Restaurant restaurant;
    private List<Dish> dayMenu;
    private int votes;

    public DayMenuTo(Date date, Restaurant restaurant, List<Dish> dayMenu, int votes) {
        this.date = date;
        this.restaurant = restaurant;
        this.dayMenu = dayMenu;
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Ресторан: " + restaurant.getName() + ";\n"
                + "Меню дня: " + "\n"
                + dayMenu + "\n"
                + "Количаство голосов: " + votes;
    }
}
