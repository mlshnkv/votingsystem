package org.moloshnikov.votingsystem.web;

import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.service.VoteService;
import org.moloshnikov.votingsystem.to.RestaurantTo;
import org.moloshnikov.votingsystem.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = VotingController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VotingController {
    private final Logger log = LoggerFactory.getLogger(VotingController.class);
    public static final String REST_URL = "/voting";

    private final VoteService voteService;

    public VotingController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return voteService.getAll();
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
        Vote vote = voteService.toVote(restaurant);
        log.info("give vote for restaurant {}", vote.getRestaurant().getId());
        return new ResponseEntity<>(vote, HttpStatus.CREATED);
    }
}