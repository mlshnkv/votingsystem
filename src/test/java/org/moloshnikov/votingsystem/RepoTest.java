package org.moloshnikov.votingsystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Map;

@SpringJUnitConfig(locations = {"classpath:spring/spring-mvc.xml", "classpath:db/spring-db.xml"})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ExtendWith(TimingExtension.class)
@Transactional
public class RepoTest {
    @Autowired
    VoteRepository repository;

//    @Test
//    public void test() {
//        Map<Restaurant, Integer> restaurantWithVotes = repository.getRestaurantWithVotes(LocalDate.now());
//        for (Map.Entry<Restaurant, Integer> stringIntegerEntry : restaurantWithVotes.entrySet()) {
//            System.out.println(stringIntegerEntry.getKey() + " = " + stringIntegerEntry.getValue());
//        }
//    }
}
