package jpa.basic.schedule.domain;

import jakarta.persistence.*;
import jpa.basic.schedule.validation.Validator;
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

    @Column(length = 100, nullable = false)
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
        Validator.isNotNull(content, "댓글 내용은 필수 입력입니다!");
        Validator.checkLength(content, 100, "댓글 내용을 최소(1) ~ 최대(100) 범위에 맞게 작성하세요!");
        Validator.isNotNull(name, "작성자명은 필수 입력입니다!");
        Validator.checkLength(name, 20, "작성자명을 최소(1) ~ 최대(20) 범위에 맞게 작성하세요!");
        Validator.isNotNull(password, "비밀번호는 필수 입력입니다!");
        Validator.checkLength(password, 20, "비밀번호를 최소(1) ~ 최대(20) 범위에 맞게 작성하세요!");

        this.scheduleId = scheduleId;
        this.content = content;
        this.name = name;
        this.password = password;
    }
}
