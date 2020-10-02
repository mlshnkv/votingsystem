package org.moloshnikov.votingsystem.service;

import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.repository.RestaurantRepository;
import org.moloshnikov.votingsystem.repository.UserRepository;
import org.moloshnikov.votingsystem.repository.VoteRepository;
import org.moloshnikov.votingsystem.util.ValidationUtil;
import org.moloshnikov.votingsystem.util.VotingUtil;
import org.moloshnikov.votingsystem.util.exception.IllegalRequestDataException;
import org.moloshnikov.votingsystem.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.moloshnikov.votingsystem.util.ValidationUtil.checkNotFoundWithDate;
import static org.moloshnikov.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public void delete(int userId) {
        LocalDateTime now = LocalDateTime.now();
        ValidationUtil.checkDeadLine(now.toLocalTime());
        checkNotFoundWithDate(voteRepository.delete(userId, now.toLocalDate()) != 0, now.toLocalDate());
    }

    public Vote get(LocalDate localDate, int userId) {
        Vote vote = voteRepository.getByUserIdDate(userId, localDate);
        return vote;
    }

    @Transactional
    public Vote toVote(Restaurant restaurantId, int userId) {
        Restaurant restaurant = restaurantRepository.getOne(restaurantId.getId());
        Assert.notNull(restaurant, "restaurant must be not null");
        LocalDateTime now = LocalDateTime.now();
        Vote vote = voteRepository.getByUserIdDate(userId, now.toLocalDate());
        Restaurant selectedRestaurant = checkNotFoundWithId(restaurant, restaurant.getId());
        if (vote == null) {
            vote = VotingUtil.makeVote(selectedRestaurant, userRepository.findById(userId).orElse(null));
        } else {
            throw new IllegalRequestDataException("Sorry, you can only re-vote");
        }
        return voteRepository.save(vote);
    }

    @Transactional
    public Vote reVote(Restaurant restaurantId, int userId) {
        LocalDateTime now = LocalDateTime.now();
        ValidationUtil.checkDeadLine(now.toLocalTime());
        Vote vote = voteRepository.getByUserIdDate(userId, now.toLocalDate());
        if (vote == null) {
            throw new NotFoundException("Sorry, you need to vote first");
        }
        Restaurant restaurant = restaurantRepository.getOne(restaurantId.getId());
        Assert.notNull(restaurant, "restaurant must be not null");
        Restaurant selectedRestaurant = checkNotFoundWithId(restaurant, restaurant.getId());
            vote.setRestaurant(selectedRestaurant);
            vote.setLocalDate(now.toLocalDate());
            vote.setLocalTime(now.toLocalTime());
        return voteRepository.save(vote);
    }
}