package com.example.sbbmissoin;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime creatDate;

    @OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}
