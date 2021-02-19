package ru.otus.xmlconfiguration.util;

import ru.otus.xmlconfiguration.domain.Answer;
import ru.otus.xmlconfiguration.domain.Question;

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
