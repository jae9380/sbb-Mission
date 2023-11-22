package com.example.sbbmissoin.answer.service;

import com.example.sbbmissoin.answer.entity.Answer;
import com.example.sbbmissoin.answer.repository.AnswerRepository;
import com.example.sbbmissoin.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question,String content){
        Answer answer=new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        this.answerRepository.save(answer);
    }
}
