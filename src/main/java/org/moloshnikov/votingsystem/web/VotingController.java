package org.moloshnikov.votingsystem.web;

import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.service.MenuService;
import org.moloshnikov.votingsystem.service.VoteService;
import org.moloshnikov.votingsystem.to.RestaurantTo;
import org.moloshnikov.votingsystem.util.SecurityUtil;
import org.moloshnikov.votingsystem.util.VotingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VotingController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VotingController {
    private final Logger log = LoggerFactory.getLogger(VotingController.class);
    public static final String REST_URL = "/voting";

    private final VoteService voteService;
    private final MenuService menuService;

    public VotingController(VoteService voteService, MenuService menuService) {
        this.voteService = voteService;
        this.menuService = menuService;
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return VotingUtil.getTos(menuService.getMenusByDate(LocalDate.now()));
    }

    @GetMapping(value = "/date")
    public Vote get(@RequestParam @Nullable LocalDate date) {
        int userId = SecurityUtil.authUserId();
        log.info("get vote for {}", date);
        return voteService.get(date, userId);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        int userId = SecurityUtil.authUserId();
        voteService.delete(userId);
        log.info("delete vote for user {}", userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> toVote(@RequestBody Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        Vote vote = voteService.toVote(restaurant, userId);
        log.info("give vote for restaurant {}", vote.getRestaurant().getId());
        return new ResponseEntity<>(vote, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void reVote(@RequestBody Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        Vote vote = voteService.reVote(restaurant, userId);
        log.info("re-vote for restaurant {}", vote.getRestaurant().getId());
    }
}