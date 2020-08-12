package org.moloshnikov.votingsystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
public class Vote extends AbstractBaseEntity{


    private final LocalDateTime localDateTime;
    private final DayMenu dayMenu;
    private final User user;

    public Vote(LocalDateTime localDateTime, DayMenu dayMenu, User user) {
        this.localDateTime = localDateTime;
        this.dayMenu = dayMenu;
        this.user = user;
    }

    public Vote(Integer id, LocalDateTime localDateTime, DayMenu dayMenu, User user) {
        super(id);
        this.localDateTime = localDateTime;
        this.dayMenu = dayMenu;
        this.user = user;
    }

    public DayMenu getDayMenu() {
        return dayMenu;
    }
}
