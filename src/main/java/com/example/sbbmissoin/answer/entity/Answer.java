package com.example.sbbmissoin.answer.entity;

import com.example.sbbmissoin.question.entity.Question;
import com.example.sbbmissoin.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    private LocalDateTime createDate;

    @ManyToOne
    private Question question;

    @ManyToOne
    private UserEntity author;

    private LocalDateTime modifyDate;
}
