package org.moloshnikov.votingsystem.service;

import org.moloshnikov.votingsystem.model.Menu;
import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.repository.menu.MenuRepository;
import org.moloshnikov.votingsystem.repository.restaurant.RestaurantRepository;
import org.moloshnikov.votingsystem.repository.user.UserRepository;
import org.moloshnikov.votingsystem.repository.vote.VoteRepository;
import org.moloshnikov.votingsystem.to.BaseTo;
import org.moloshnikov.votingsystem.to.RestaurantTo;
import org.moloshnikov.votingsystem.util.SecurityUtil;
import org.moloshnikov.votingsystem.util.ValidationUtil;
import org.moloshnikov.votingsystem.util.VotingUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.moloshnikov.votingsystem.util.ValidationUtil.checkNotFoundWithDate;
import static org.moloshnikov.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
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

    @Cacheable("votes")
    public List<RestaurantTo> getAll() {
        LocalDate date = LocalDate.now();
        List<Restaurant> restaurants = restaurantRepository.getAll();
        List<Menu> menus = menuRepository.getAllByDate(date);
        List<Vote> votes = voteRepository.getAllByDay(date);
        return VotingUtil.getTos(restaurants, menus, votes);
    }

    @Transactional
    @CacheEvict(value = "votes", allEntries = true)
    public void delete(int userId) {
        LocalDateTime now = LocalDateTime.now();
        ValidationUtil.checkDeadLine(now.toLocalTime());
        checkNotFoundWithDate(voteRepository.delete(userId, now.toLocalDate()), now.toLocalDate());
    }

    @Transactional
    @CacheEvict(value = "votes", allEntries = true)
    public Vote toVote(Restaurant restaurantId) {
        Restaurant restaurant = restaurantRepository.get(restaurantId.getId());
        Assert.notNull(restaurant, "restaurant must be not null");
        LocalDateTime now = LocalDateTime.now();
        ValidationUtil.checkDeadLine(now.toLocalTime());

        Restaurant selectedRestaurant = checkNotFoundWithId(restaurantRepository.get(restaurant.id()), restaurant.getId());

        int userId = SecurityUtil.authUserId();

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
