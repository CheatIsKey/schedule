package jpa.basic.schedule.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String name;

    private String password;

    public Schedule(Long id, String title, String content, String name, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
    }

//    @OneToMany(mappedBy = "comment_id")
//    private List<Comment> comments = new ArrayList<>();




}


