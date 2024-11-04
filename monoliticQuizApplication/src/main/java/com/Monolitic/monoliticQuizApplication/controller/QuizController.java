package com.Monolitic.monoliticQuizApplication.controller;

import com.Monolitic.monoliticQuizApplication.dao.QuestionWrapper;
import com.Monolitic.monoliticQuizApplication.dao.Responses;
import com.Monolitic.monoliticQuizApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numberOfQ, @RequestParam String title ){
        return quizService.createQuiz(category,numberOfQ,title);
    }
    @GetMapping("/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable int quizId){
        return quizService.getQuestions(quizId);
    }
    @PostMapping("/submit/{quizId}")
    public ResponseEntity<Integer> submitResponse(@PathVariable int quizId, @RequestBody List<Responses> response){
        return quizService.calculateResponse(quizId,response);
    }
}
