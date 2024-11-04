package com.Monolitic.monoliticQuizApplication.service;

import com.Monolitic.monoliticQuizApplication.dao.Questions;
import com.Monolitic.monoliticQuizApplication.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionsRepository quizQuestionsRepository;
    public ResponseEntity<List<Questions>> getAllQuestions(){
        try {
            return new ResponseEntity<>(quizQuestionsRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category){
        try {
            return new ResponseEntity<>(quizQuestionsRepository.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<String> addQuestion(Questions quizQuestions){

        quizQuestionsRepository.save(quizQuestions);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

}
