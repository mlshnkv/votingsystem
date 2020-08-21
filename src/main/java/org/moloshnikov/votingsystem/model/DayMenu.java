package org.moloshnikov.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "day_menus")
public class DayMenu extends AbstractBaseEntity {
    @Column(name = "date", columnDefinition = "timestamp default now()")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value = "restaurant-dayMenu")
//    @NotNull
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dayMenu", cascade = CascadeType.REMOVE)
//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "dayMenu-dish")
    // @JsonDeserialize(as = List.class)
    private Set<Dish> dishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dayMenu", cascade = CascadeType.REMOVE)
    //, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "dayMenu-vote")
    private Set<Vote> votes;

    public DayMenu() {
    }

    public DayMenu(LocalDate date, Restaurant restaurant, Set<Dish> dishes) {
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    public DayMenu(Integer id, LocalDate date, Restaurant restaurant, Set<Dish> dishes) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = dishes;
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

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dayMenu) {
        this.dishes = dayMenu;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "DayMenu{" +
                "date=" + date +
                ", restaurant=" + restaurant +
                ", dayMenu=" + dishes +
                ", votes=" + votes +
                ", id=" + id +
                '}';
    }
}
