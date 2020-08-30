package org.moloshnikov.votingsystem.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.moloshnikov.votingsystem.TimingExtension;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = {"classpath:spring/spring-mvc.xml", "classpath:db/spring-db.xml"})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ExtendWith(TimingExtension.class)
//@Transactional
abstract public class AbstractServiceTest {
}