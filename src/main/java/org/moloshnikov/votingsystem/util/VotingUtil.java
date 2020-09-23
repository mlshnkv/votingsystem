package org.moloshnikov.votingsystem.util;

import org.moloshnikov.votingsystem.model.Menu;
import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.model.User;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.to.RestaurantTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class VotingUtil {
    public static final LocalTime ORIGINAL_TIME = LocalTime.of(11, 0);
    public static LocalTime deadLine = ORIGINAL_TIME;

    public static void setDeadLineForTest(LocalTime time) {
        deadLine = time;
    }

    private VotingUtil() {
    }

    public static List<RestaurantTo> getTos(List<Menu> menus) {
        return menus.stream()
                .map(menu -> new RestaurantTo(menu.getRestaurant().getId(), menu.getRestaurant().getName(), menu.getDishes()))
                .collect(Collectors.toList());
    }

    public static Vote makeVote(Restaurant restaurant, User user) {
        return new Vote(LocalDate.now(), LocalTime.now(), restaurant, user);
    }
}