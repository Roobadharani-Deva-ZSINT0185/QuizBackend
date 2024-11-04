package com.Monolitic.monoliticQuizApplication.dao;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
public class QuestionWrapper {
    private int id;
    private String questionTitle;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
