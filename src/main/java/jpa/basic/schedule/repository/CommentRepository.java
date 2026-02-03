package jpa.basic.schedule.repository;

import jpa.basic.schedule.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    long countByScheduleId(Long scheduleId);

    List<Comment> findCommentsByScheduleId(Long scheduleId);
}
