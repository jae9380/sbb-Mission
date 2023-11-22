package com.example.sbbmissoin.question.service;

import com.example.sbbmissoin.question.entity.Question;
import com.example.sbbmissoin.DataNotFoundException;
import com.example.sbbmissoin.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id){
        Optional<Question>question = this.questionRepository.findById(id);
        if (question.isPresent()){
            return question.get();
        }else {
            throw new DataNotFoundException("question is not found");
        }
    }
}