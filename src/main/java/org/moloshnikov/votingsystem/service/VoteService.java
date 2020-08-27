package org.moloshnikov.votingsystem.service;

import org.moloshnikov.votingsystem.model.Menu;
import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.repository.daymenu.MenuRepository;
import org.moloshnikov.votingsystem.repository.restaurant.RestaurantRepository;
import org.moloshnikov.votingsystem.repository.user.UserRepository;
import org.moloshnikov.votingsystem.repository.vote.VoteRepository;
import org.moloshnikov.votingsystem.to.RestaurantTo;
import org.moloshnikov.votingsystem.util.SecurityUtil;
import org.moloshnikov.votingsystem.util.ValidationUtil;
import org.moloshnikov.votingsystem.util.VotingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.moloshnikov.votingsystem.util.ValidationUtil.checkNotFoundWithDate;

@Service
public class VoteService {
    private final Logger log = LoggerFactory.getLogger(VoteService.class);
    private final VoteRepository voteRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository voteRepository, MenuRepository menuRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantTo> getAll() {
        LocalDate date = LocalDate.now();
        List<Restaurant> restaurants = restaurantRepository.getAll();
        for (Restaurant restaurant : restaurants) {
            log.debug(restaurant.toString());
        }
        List<Menu> menus = menuRepository.getAllByDate(date);
        for (Menu menu : menus) {
            log.debug(menu.toString());
        }

        List<Vote> votes = voteRepository.getAllByDay(date);
        log.debug(votes.toString());
        List<RestaurantTo> restaurantTos = VotingUtil.getTos(restaurants, menus, votes);
        for (RestaurantTo restaurantTo : restaurantTos) {
            log.debug(restaurantTo.toString());
        }
        return restaurantTos;
    }

    @Transactional
    public void delete(int userId) {
        LocalDateTime now = LocalDateTime.now();
        ValidationUtil.checkDeadLine(now.toLocalTime());
        checkNotFoundWithDate(voteRepository.delete(userId, now.toLocalDate()), now.toLocalDate());
    }

    @Transactional
    public Vote toVote(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must be not null");
        LocalDateTime now = LocalDateTime.now();
        int userId = SecurityUtil.authUserId();
        ValidationUtil.checkDeadLine(now.toLocalTime());

        Restaurant selectedRestaurant = restaurantRepository.get(restaurant.id());

        Vote checkVote = voteRepository.getByUserIdDate(userId, now.toLocalDate());
        if (checkVote == null) {
            checkVote = VotingUtil.makeVote(selectedRestaurant, userRepository.get(userId));
        } else {
            checkVote.setRestaurant(selectedRestaurant);
            checkVote.setLocalDate(now.toLocalDate());
            checkVote.setLocalTime(now.toLocalTime());
        }
        return voteRepository.save(checkVote);
    }
}
