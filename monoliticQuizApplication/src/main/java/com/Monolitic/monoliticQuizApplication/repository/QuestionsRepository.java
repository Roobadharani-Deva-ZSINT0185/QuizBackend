package com.Monolitic.monoliticQuizApplication.repository;

import com.Monolitic.monoliticQuizApplication.dao.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
    List<Questions> findByCategory(String category);
    @Query(value = "select * from questions q where q.category = :category ORDER BY RAND() LIMIT :numberOfQ",nativeQuery = true)
    List<Questions> findRandomQuestionsByCategory(@Param("category") String category, @Param("numberOfQ") int numberOfQ);
}
