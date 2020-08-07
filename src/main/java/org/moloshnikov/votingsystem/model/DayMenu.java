package org.moloshnikov.votingsystem.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayMenu extends AbstractBaseEntity {

    {
        dayMenu = new ArrayList<>();
    }
    private Date date;
    private Restaurant restaurant;
    private List<Dish> dayMenu;

    public DayMenu(Date date, Restaurant restaurant) {
        this.date = date;
        this.restaurant = restaurant;
    }

    public DayMenu(Integer id, Date date, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDayMenu() {
        return dayMenu;
    }

    public void setDayMenu(List<Dish> dayMenu) {
        this.dayMenu = dayMenu;
    }

    public void addDish(Dish dish) {
        dayMenu.add(dish);
    }
}
