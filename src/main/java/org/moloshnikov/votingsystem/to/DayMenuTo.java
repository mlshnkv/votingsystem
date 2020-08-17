package org.moloshnikov.votingsystem.to;

import org.moloshnikov.votingsystem.model.Dish;
import org.moloshnikov.votingsystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public class DayMenuTo {
    private final int id;
    private final LocalDate date;
    private final Restaurant restaurant;
    private final List<Dish> dayMenu;
    private final int votes;

    public DayMenuTo(int id, LocalDate date, Restaurant restaurant, List<Dish> dayMenu, int votes) {
        this.id = id;
        this.date = date;
        this.restaurant = restaurant;
        this.dayMenu = dayMenu;
        this.votes = votes;
    }

    public LocalDate getDate() {
        return date;
    }

    public Restaurant getRestaurant() {
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
