package org.moloshnikov.votingsystem.util;

import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.model.User;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.to.RestaurantTo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VotingUtil {

    public static List<RestaurantTo> getTos(Collection<Vote> votes) {
        Map<Restaurant, Integer> votesByRestaurant = votes.stream()
                .collect(
                        Collectors.groupingBy(Vote::getRestaurant, Collectors.summingInt(value -> 1))
                );
        return votesByRestaurant.keySet().stream()
                .map(restaurant -> createTo(restaurant, votesByRestaurant.get(restaurant)))
                .collect(Collectors.toList());
    }

    public static RestaurantTo createTo(Restaurant restaurant, int votes) {
        return new RestaurantTo(restaurant.getName(), restaurant.getMenu(), votes);
    }

    public static Vote makeVote(Restaurant restaurant, User user) {
        return new Vote(LocalDateTime.now(), restaurant, user);
    }
}
