package org.moloshnikov.votingsystem.to;

import org.moloshnikov.votingsystem.model.Dish;

import java.time.LocalDate;
import java.util.List;

public class DayMenuTo {

    private final LocalDate date;
    private final String restaurant;
    private final List<Dish> dayMenu;
    private final int votes;

    public DayMenuTo(LocalDate date, String restaurant, List<Dish> dayMenu, int votes) {
        this.date = date;
        this.restaurant = restaurant;
        this.dayMenu = dayMenu;
        this.votes = votes;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public List<Dish> getDayMenu() {
        return dayMenu;
    }

    public int getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "Ресторан: " + restaurant + ";\n"
                + "Меню дня: " + "\n"
                + dayMenu + "\n"
                + "Количаство голосов: " + votes;
    }


//    @Override
//    public String toString() {
//        return "Ресторан: " + restaurant + " - " + votes;
//    }
}
