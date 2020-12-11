package ru.otus.homework3.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework3.config.AppProps;
import ru.otus.homework3.dao.QuestionDao;
import ru.otus.homework3.domain.Answer;
import ru.otus.homework3.domain.Question;
import ru.otus.homework3.domain.User;
import ru.otus.homework3.exception.QuestionsReadingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;
    private final UserService userService;
    private final ReadWriteService readWriteService;
    private final MessageSource messageSource;
    private final AppProps props;
    private final int passMark;

    public QuestionServiceImpl(QuestionDao questionDao, UserService userService, ReadWriteService readWriteService,
                               MessageSource messageSource, AppProps props, @Value("${pass-mark}") int passMark) {
        this.questionDao = questionDao;
        this.userService = userService;
        this.readWriteService = readWriteService;
        this.messageSource = messageSource;
        this.props = props;
        this.passMark = passMark;
    }

    @Override
    public void runTest() {
        List<Question> questionList = getQuestions();

        User user = userService.getUser();
        if (user == null) return;

        String welcome = messageSource.getMessage("welcome-test", new String[]{user.getFullName()}, props.getLocale());

        readWriteService.print(welcome);

        int userResult = getUserResult(questionList);

        viewTestingResult(user, userResult);
    }

    private List<Question> getQuestions() {
        List<Question> questionList = null;
        try {
            questionList = questionDao.getQuestionList();
        } catch (QuestionsReadingException e) {
            readWriteService.print(messageSource.getMessage("file-not-found", new String[]{}, props.getLocale()));
            return new ArrayList<>();
        } catch (Exception ex) {
            readWriteService.print(messageSource.getMessage("resource-error", new String[]{}, props.getLocale()));
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
            readWriteService.print(messageSource.getMessage("congrats", new String[]{user.getFullName()}, props.getLocale()));
        } else {
            readWriteService.print(messageSource.getMessage("sorry", new String[]{user.getFullName()}, props.getLocale()));
        }
        readWriteService.print(messageSource.getMessage("score", new String[]{String.valueOf(userResult)}, props.getLocale()));
    }
}
