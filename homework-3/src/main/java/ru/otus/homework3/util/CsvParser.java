package ru.otus.homework3.util;

import org.springframework.stereotype.Component;
import ru.otus.homework3.domain.Answer;
import ru.otus.homework3.domain.Question;

import java.util.ArrayList;
import java.util.List;

@Component
public class CsvParser {
    public Question parse(String arg) {
        String[] split = arg.split(",");
        Question question = new Question();
        if (split.length > 0) {
            question.setQuestionText(split[0]);
            List<Answer> answerList = new ArrayList<>();
            for (int i = 1; i < split.length; i++) {
                answerList.add(new Answer(split[i], i == 1));
            }
            question.setAnswerList(answerList);
        }

        return question;
    }
}
