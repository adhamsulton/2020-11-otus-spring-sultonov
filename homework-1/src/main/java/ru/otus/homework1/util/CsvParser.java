package ru.otus.homework1.util;

import ru.otus.homework1.domain.Answer;
import ru.otus.homework1.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    public Question parse(String arg) {
        String[] split = arg.split(",");
        Question question = new Question();
        if (split.length > 0) {
            question.setQuestionText(split[0]);
            List<Answer> answerList = new ArrayList<>();
            for (int i = 1; i < split.length; i++) {
                answerList.add(new Answer(split[i]));
            }
            question.setAnswerList(answerList);
        }

        return question;
    }
}
