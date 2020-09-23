package org.moloshnikov.votingsystem.web;

import org.junit.jupiter.api.Test;
import org.moloshnikov.votingsystem.service.VoteService;
import org.moloshnikov.votingsystem.web.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;

import static org.moloshnikov.votingsystem.TestData.*;
import static org.moloshnikov.votingsystem.TestUtil.userHttpBasic;
import static org.moloshnikov.votingsystem.util.VotingUtil.ORIGINAL_TIME;
import static org.moloshnikov.votingsystem.util.VotingUtil.setDeadLineForTest;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VotingControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VotingController.REST_URL + '/';

    @Autowired
    VoteService voteService;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_MATCHER.contentJson(RESTAURANT_TOS_TODAY));
    }

    @Test
    void delete() throws Exception {
        setDeadLineForTest(LocalTime.now().plusMinutes(1));
        perform(MockMvcRequestBuilders.delete(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());
        setDeadLineForTest(ORIGINAL_TIME);
    }

    @Test
    void deleteNotFound() throws Exception {
        setDeadLineForTest(LocalTime.now().plusMinutes(1));
        voteService.delete(USER_ID);
        perform(MockMvcRequestBuilders.delete(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity());
        setDeadLineForTest(ORIGINAL_TIME);
    }

    @Test
    void reVote() throws Exception {
        setDeadLineForTest(LocalTime.now().plusMinutes(1));
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(RESTAURANT_1_WITHOUT_NAME))
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());
        setDeadLineForTest(ORIGINAL_TIME);
    }

    @Test
    void putDeadlineVote() throws Exception {
        setDeadLineForTest(LocalTime.now().minusNanos(1));
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(RESTAURANT_1_WITHOUT_NAME))
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
        setDeadLineForTest(ORIGINAL_TIME);
    }
}