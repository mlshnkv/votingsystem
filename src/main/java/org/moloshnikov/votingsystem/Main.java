package org.moloshnikov.votingsystem;

import org.moloshnikov.votingsystem.model.Dish;
import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.model.User;
import org.moloshnikov.votingsystem.repository.VoteRepository;
import org.moloshnikov.votingsystem.to.RestaurantTo;
import org.moloshnikov.votingsystem.util.VotingUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        VoteRepository voteRepository = new VoteRepository();
        Restaurant restaurant = new Restaurant("Биша-Муда");
        Restaurant restaurant1 = new Restaurant("Ваша-Наша");

        User user0 = new User();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        User user5 = new User();
        User user6 = new User();

        Dish dish1 = new Dish("Каша", 2);
        Dish dish2 = new Dish("Картошка", 5);
        Dish dish3 = new Dish("Макароны", 3);
        Dish dish4 = new Dish("Овощи", 6);

        restaurant.addDishToMenu(dish1);
        restaurant.addDishToMenu(dish2);
        restaurant.addDishToMenu(dish3);
        restaurant.addDishToMenu(dish4);

        voteRepository.putVote(VotingUtil.makeVote(restaurant, user0));
        voteRepository.putVote(VotingUtil.makeVote(restaurant, user1));
        voteRepository.putVote(VotingUtil.makeVote(restaurant, user2));
        voteRepository.putVote(VotingUtil.makeVote(restaurant, user3));
        voteRepository.putVote(VotingUtil.makeVote(restaurant, user4));
        voteRepository.putVote(VotingUtil.makeVote(restaurant1, user5));
        voteRepository.putVote(VotingUtil.makeVote(restaurant1, user6));

        List<RestaurantTo> listOfRestaurantTo = VotingUtil.getTos(voteRepository.getVotes());

        for (RestaurantTo restaurantTo : listOfRestaurantTo) {
            System.out.println(restaurantTo);
        }


    }
}
