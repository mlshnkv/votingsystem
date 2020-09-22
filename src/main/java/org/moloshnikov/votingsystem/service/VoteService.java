package org.moloshnikov.votingsystem.service;

import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.repository.MenuRepository;
import org.moloshnikov.votingsystem.repository.RestaurantRepository;
import org.moloshnikov.votingsystem.repository.UserRepository;
import org.moloshnikov.votingsystem.repository.VoteRepository;
import org.moloshnikov.votingsystem.to.RestaurantTo;
import org.moloshnikov.votingsystem.util.ValidationUtil;
import org.moloshnikov.votingsystem.util.VotingUtil;
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

    public List<RestaurantTo> getAll() {
        return VotingUtil.getTos(menuRepository.getAllByDate(LocalDate.now()));
    }

    public void delete(int userId) {
        LocalDateTime now = LocalDateTime.now();
        ValidationUtil.checkDeadLine(now.toLocalTime());
        checkNotFoundWithDate(voteRepository.delete(userId, now.toLocalDate()) != 0, now.toLocalDate());
    }


    public Vote toVote(Restaurant restaurantId, int userId) {
        Restaurant restaurant = restaurantRepository.getOne(restaurantId.getId());
        Assert.notNull(restaurant, "restaurant must be not null");
        LocalDateTime now = LocalDateTime.now();
        ValidationUtil.checkDeadLine(now.toLocalTime());

        Restaurant selectedRestaurant = checkNotFoundWithId(restaurant, restaurant.getId());

        Vote checkVote = voteRepository.getByUserIdDate(userId, now.toLocalDate());
        if (checkVote == null) {
            checkVote = VotingUtil.makeVote(selectedRestaurant, userRepository.findById(userId).orElse(null));
        } else {
            checkVote.setRestaurant(selectedRestaurant);
            checkVote.setLocalDate(now.toLocalDate());
            checkVote.setLocalTime(now.toLocalTime());
        }
        return voteRepository.save(checkVote);
    }
}
