package com.Monolitic.monoliticQuizApplication.service;

import com.Monolitic.monoliticQuizApplication.dao.QuestionWrapper;
import com.Monolitic.monoliticQuizApplication.dao.Quiz;
import com.Monolitic.monoliticQuizApplication.dao.Questions;
import com.Monolitic.monoliticQuizApplication.dao.Responses;
import com.Monolitic.monoliticQuizApplication.repository.QuestionsRepository;
import com.Monolitic.monoliticQuizApplication.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionsRepository questionsRepository;

    public ResponseEntity<String> createQuiz(String category, int numberOfQ, String title){
        try {
            List<Questions> questions = questionsRepository.findRandomQuestionsByCategory(category, numberOfQ);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizRepository.save(quiz);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(int quizId){
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        List<Questions> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Questions q: questionsFromDb){
            QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(),quiz.get().getTitle(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(questionWrapper);
        }
        return new ResponseEntity<>(questionsForUser,HttpStatus.FOUND);
    }

    public ResponseEntity<Integer> calculateResponse(int quizId, List<Responses> responses){
        Quiz quiz = quizRepository.findById(quizId).get();
        List<Questions> questions = quiz.getQuestions();
        int i=0,right=0;
        for(Questions q:questions){
            if(q.getCorrectAnswer().equals(responses.get(i).getResponse())){
                right++;
            }i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
