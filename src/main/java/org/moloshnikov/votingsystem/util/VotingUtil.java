package org.moloshnikov.votingsystem.util;

import org.moloshnikov.votingsystem.model.Menu;
import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.model.User;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.to.RestaurantTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VotingUtil {

    public static final LocalTime STUB_DEADLINE = LocalTime.of(10, 0);
    public static LocalTime deadLine = STUB_DEADLINE;

    public static void setDeadLine(LocalTime time) {
        deadLine = time;
    }

    private VotingUtil() {
    }

    public static List<RestaurantTo> getTos(List<Restaurant> restaurants, List<Menu> menus, List<Vote> votes) {
        List<RestaurantTo> restaurantTos = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            int id = restaurant.getId();
            String name = restaurant.getName();

            RestaurantTo restaurantTo = new RestaurantTo(id, name);
            for (Menu menu : menus) {
                if (restaurant.equals(menu.getRestaurant())) {
                    restaurantTo.setMenu(menu.getDishes());
                    break;
                }
            }
            for (Vote vote : votes) {
                if (restaurant.equals(vote.getRestaurant())) {
                    restaurantTo.setVotes(restaurantTo.getVotes() + 1);
                }
            }
            restaurantTos.add(restaurantTo);
        }
        return restaurantTos.stream()
                .sorted(Comparator.comparingInt(RestaurantTo::getVotes).reversed())
                .collect(Collectors.toList());
    }

    public static Vote makeVote(Restaurant restaurant, User user) {
        return new Vote(LocalDate.now(), LocalTime.now(), restaurant, user);
    }
}
