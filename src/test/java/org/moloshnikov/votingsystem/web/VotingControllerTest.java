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
import static org.moloshnikov.votingsystem.util.ValidationUtil.STUB_DEADLINE;
import static org.moloshnikov.votingsystem.util.ValidationUtil.setDeadLine;
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
        perform(MockMvcRequestBuilders.delete(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteNotFound() throws Exception {
        voteService.delete(USER_ID);
        perform(MockMvcRequestBuilders.delete(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isNotFound());
    }


    @Test
    void toVote() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(RESTAURANT_1_WITHOUT_NAME))
                .with(userHttpBasic(USER)))
                .andExpect(status().isCreated());
    }

    @Test
    void postDeadlineVote() throws Exception {
        setDeadLine(LocalTime.now().minusNanos(1));
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(RESTAURANT_1_WITHOUT_NAME))
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
        setDeadLine(STUB_DEADLINE);
    }
}