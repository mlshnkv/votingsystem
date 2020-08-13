package org.moloshnikov.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
public class Vote extends AbstractBaseEntity{
    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
//    @NotNull
    private DayMenu dayMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
//    @NotNull
    private User user;

    public Vote() {

    }

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
