package ru.otus.homework2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework2.dao.QuestionDao;
import ru.otus.homework2.domain.Answer;
import ru.otus.homework2.domain.Question;
import ru.otus.homework2.domain.User;
import ru.otus.homework2.exception.QuestionsReadingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;
    private final UserService userService;
    private final ReadWriteService readWriteService;
    private final int passMark;

    public QuestionServiceImpl(QuestionDao questionDao, UserService userService, ReadWriteService readWriteService,
                               @Value("${pass-mark}") int passMark) {
        this.questionDao = questionDao;
        this.userService = userService;
        this.readWriteService = readWriteService;
        this.passMark = passMark;
    }

    @Override
    public void runTest() {
        List<Question> questionList = getQuestions();

        User user = userService.getUserFullName();
        if (user == null) return;

        readWriteService.print("Welcome to testing " + user.getFullName());

        int userResult = getUserResult(questionList);

        viewTestingResult(user, userResult);
    }

    private List<Question> getQuestions() {
        List<Question> questionList = null;
        try {
            questionList = questionDao.getQuestionList();
        } catch (QuestionsReadingException e) {
            readWriteService.print("The corresponding test file was not found in the resources.");
            return new ArrayList<>();
        } catch (Exception ex) {
            readWriteService.print("Unexpected error by getting resource");
            return new ArrayList<>();
        }

        shuffleAnswers(questionList);
        return questionList;
    }

    private void shuffleAnswers(List<Question> questionList) {
        questionList.forEach(question -> {
            Collections.shuffle(question.getAnswerList());
        });
    }

    private int getUserResult(List<Question> questionList) {
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
        return userResult;
    }

    private void viewTestingResult(User user, int userResult) {
        if (userResult >= passMark) {
            readWriteService.print("Congrats, " + user.getFullName() + " you're passed test");
        } else {
            readWriteService.print("Sorry, " + user.getFullName() + " you can't pass test");
        }
        readWriteService.print("Your score: " + userResult);
    }
}
