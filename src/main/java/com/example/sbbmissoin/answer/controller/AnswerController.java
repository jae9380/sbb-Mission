package com.example.sbbmissoin.answer.controller;

import com.example.sbbmissoin.answer.service.AnswerService;
import com.example.sbbmissoin.question.entity.Question;
import com.example.sbbmissoin.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{id}")
    public String createAnswer(
            Model model, @PathVariable("id")Integer id,@PathVariable String content
    ){
        Question question=this.questionService.getQuestion(id);
        this.answerService.create(question,content);

        return String.format("redirect:/question/detail/%s",id);
    }
}
