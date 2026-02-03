package jpa.basic.schedule.domain;

import jakarta.persistence.*;
import jpa.basic.schedule.validation.Validator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(length = 30, nullable = false)
    private String title;

//    @Column(length = 200, nullable = false)
    private String content;

//    @Column(length = 20, nullable = false)
    private String name;

//    @Column(length = 20, nullable = false)
    private String password;

    public Schedule(String title, String content, String name, String password) {
        /**
         * DTO 뿐만 아니라, 엔티티에서도 생성하기 전에 검증하면서 두 번 확인한다.
         */
        Validator.isNotNull(title, "일정 제목은 필수 입력입니다!");
        Validator.checkLength(title, 30, "일정 제목을 최소(1) ~ 최대(30) 범위에 맞게 작성하세요!");
        Validator.isNotNull(content, "일정 내용은 필수 입력입니다!");
        Validator.checkLength(content, 200, "일정 내용을 최소(1) ~ 최대(200) 범위에 맞게 작성하세요!");
        Validator.isNotNull(name, "작성자명은 필수 입력입니다!");
        Validator.checkLength(name, 20, "작성자명을 최소(1) ~ 최대(20) 범위에 맞게 작성하세요!");
        Validator.isNotNull(password, "비밀번호는 필수 입력입니다!");
        Validator.checkLength(password, 20, "비밀번호를 최소(1) ~ 최대(20) 범위에 맞게 작성하세요!");

        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
    }

//    @OneToMany(mappedBy = "schedule")
//    private List<Comment> comments = new ArrayList<>();

    public void changeScheduleTitle(String title) {
        this.title = title;
    }

    public void changeScheduleName(String name) {
        this.name = name;
    }
}


