package com.example.sbbmissoin.answer.controller;

import com.example.sbbmissoin.answer.entity.AnswerForm;
import com.example.sbbmissoin.answer.service.AnswerService;
import com.example.sbbmissoin.question.entity.Question;
import com.example.sbbmissoin.question.service.QuestionService;
import com.example.sbbmissoin.user.model.UserEntity;
import com.example.sbbmissoin.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(
            Model model, @PathVariable("id")Integer id,
            @Valid AnswerForm answerForm,
            BindingResult bindingResult,
            Principal principal
            ){
        UserEntity userEntity=this.userService.getUser(principal.getName());
        Question question=this.questionService.getQuestion(id);

        if (bindingResult.hasErrors()){
            model.addAttribute("question",question);
            return "question/detail";
        }
        this.answerService.create(question,answerForm.getContent(),userEntity);

        return String.format("redirect:/question/detail/%s",id);
    }
}
