package jpa.basic.schedule.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String password;

    private Long scheduleId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "schedule_id")
//    private Schedule schedule;
//
//    public void setSchedule(Schedule schedule) {
//        this.schedule = schedule;
//
//        if (schedule != null && !schedule.getComments().contains(this)) {
//            schedule.getComments().add(this);
//        }
//    }
//
//    public Comment(String content, String password, Schedule schedule) {
//        this.content = content;
//        this.password = password;
//        this.schedule = schedule;
//    }

    public Comment(Long scheduleId, String content, String name, String password) {
        this.scheduleId = scheduleId;
        this.content = content;
        this.name = name;
        this.password = password;
    }
}
