package com.Monolitic.monoliticQuizApplication.repository;

import com.Monolitic.monoliticQuizApplication.dao.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Integer > {
}
