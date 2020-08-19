package org.moloshnikov.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "day_menus")
public class DayMenu extends AbstractBaseEntity {
    @Column(name = "date", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonBackReference
//    @NotNull
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dayMenu", cascade = CascadeType.REMOVE)
//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private List<Dish> dayMenu;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dayMenu", cascade = CascadeType.REMOVE)
//, cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @JsonManagedReference
    private Set<Vote> votes;

    public DayMenu() {
    }

    public DayMenu(@NotNull LocalDate date, Restaurant restaurant, List<Dish> dayMenu) {
        this.date = date;
        this.restaurant = restaurant;
        this.dayMenu = dayMenu;
    }

    public DayMenu(Integer id, @NotNull LocalDate date, Restaurant restaurant, List<Dish> dayMenu) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.dayMenu = dayMenu;
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

    public List<Dish> getDayMenu() {
        return dayMenu;
    }

    public void setDayMenu(List<Dish> dayMenu) {
        this.dayMenu = dayMenu;
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
                ", dayMenu=" + dayMenu +
                ", votes=" + votes +
                ", id=" + id +
                '}';
    }
}
