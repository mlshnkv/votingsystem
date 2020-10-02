package org.moloshnikov.votingsystem.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "created"}, name = "menus_unique_idx")})
public class Menu extends AbstractBaseEntity {

    @Column(name = "created", columnDefinition = "timestamp default now()")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "menu_id")
    private List<Dish> dishes;

    public Menu() {
    }

    public Menu(Menu menu) {
        this(menu.getId(), menu.getDate(), menu.getRestaurant(), menu.getDishes());
    }

    public Menu(List<Dish> dishes) {
        super(null);
        this.dishes = dishes;
    }

    public Menu(int id, LocalDate date, Restaurant restaurant, List<Dish> dishes) {
        this.id = id;
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    public Menu(LocalDate date, Restaurant restaurant, List<Dish> dishes) {
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    public Menu(Integer id, LocalDate date, Restaurant restaurant, Dish... dishes) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = Arrays.asList(dishes);
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "date=" + date +
                ", menu=" + dishes +
                ", id=" + id +
                '}';
    }
}