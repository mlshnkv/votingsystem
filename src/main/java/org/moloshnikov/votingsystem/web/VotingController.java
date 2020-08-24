package org.moloshnikov.votingsystem.web;

import org.moloshnikov.votingsystem.model.Menu;
import org.moloshnikov.votingsystem.service.VoteService;
import org.moloshnikov.votingsystem.to.MenuTo;
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
    protected final Logger log = LoggerFactory.getLogger(VotingController.class);
    static final String REST_URL = "/profile/voting";

    private final VoteService voteService;

    public VotingController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping
    public List<MenuTo> getAll() {
        log.info("getAll");
        return voteService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete vote {} for user {}", id, userId);
        voteService.delete(id, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> toVote(@RequestBody Menu menu) {
        log.info("give vote for menu {}", menu.getId());
        voteService.toVote(menu);


//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(REST_URL + "/{id}")
//                .buildAndExpand(checkVote.getId()).toUri();

        return new ResponseEntity<>("Your vote is accepted.", HttpStatus.CREATED);
//
    }
}

