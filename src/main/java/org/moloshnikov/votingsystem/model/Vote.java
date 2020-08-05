package org.moloshnikov.votingsystem.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity{
    private final LocalDateTime localDateTime;
    private final Restaurant restaurant;
    private final User user;

    public Vote(LocalDateTime localDateTime, Restaurant restaurant, User user) {
        this.localDateTime = localDateTime;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
