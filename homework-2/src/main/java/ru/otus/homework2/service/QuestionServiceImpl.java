package ru.otus.homework2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework2.dao.QuestionDao;
import ru.otus.homework2.domain.Answer;
import ru.otus.homework2.domain.Question;
import ru.otus.homework2.exception.QuestionsReadingException;

import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;
    private final ReadWriteService readWriteService;
    private final int passMark;

    public QuestionServiceImpl(QuestionDao questionDao, ReadWriteService readWriteService,
                               @Value("${pass-mark}") int passMark) {
        this.questionDao = questionDao;
        this.readWriteService = readWriteService;
        this.passMark = passMark;
    }

    @Override
    public void runTest() {
        List<Question> questionList = null;
        try {
            questionList = questionDao.getQuestionList();
        } catch (QuestionsReadingException e) {
            readWriteService.print("The corresponding test file was not found in the resources.");
        } catch (Exception ex) {
            readWriteService.print("Unexpected error by getting resource");
        }
        shuffleAnswers(questionList);

        readWriteService.print("Please, enter your full name");
        String userFullName = readWriteService.read();
        readWriteService.print("Welcome to testing " + userFullName);

        int userResult = 0;
        for (Question question : questionList) {
            List<Answer> answerList = question.getAnswerList();

            readWriteService.print(question.getQuestionText());
            for (int i = 0; i < answerList.size(); i++) {
                readWriteService.print(i + 1 + answerList.get(i).getAnswerText());
            }

            int userAnswer = Integer.parseInt(readWriteService.read());
            if (answerList.get(userAnswer - 1).getCorrect()) {
                userResult++;
            }
        }
        if (userResult >= passMark) {
            readWriteService.print("Congrats, " + userFullName + " you're passed test");
        } else {
            readWriteService.print("Sorry, " + userFullName + " you can't pass test");
        }
        readWriteService.print("Your score: " + userResult);
    }

    private void shuffleAnswers(List<Question> questionList) {
        questionList.forEach(question -> {
            Collections.shuffle(question.getAnswerList());
        });
    }
}
