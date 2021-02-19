package ru.otus.springboot.service;

import org.springframework.stereotype.Service;
import ru.otus.springboot.config.AppProps;
import ru.otus.springboot.dao.QuestionDao;
import ru.otus.springboot.domain.Answer;
import ru.otus.springboot.domain.Question;
import ru.otus.springboot.domain.User;
import ru.otus.springboot.exception.QuestionsReadingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;
    private final UserService userService;
    private final ReadWriteService readWriteService;
    private final LocaleReadWriteService localeReadWriteService;
    private final AppProps props;

    public QuestionServiceImpl(QuestionDao questionDao, UserService userService, ReadWriteService readWriteService,
                               LocaleReadWriteService localeReadWriteService, AppProps props) {
        this.questionDao = questionDao;
        this.userService = userService;
        this.readWriteService = readWriteService;
        this.localeReadWriteService = localeReadWriteService;
        this.props = props;
    }

    @Override
    public void runTest() {
        List<Question> questionList = getQuestions();

        User user = userService.getUser();
        if (user == null) return;

        localeReadWriteService.printLocale("welcome-test", user.getFullName());

        int userResult = getUserResult(questionList);

        viewTestingResult(user, userResult);
    }

    private List<Question> getQuestions() {
        List<Question> questionList = null;
        try {
            questionList = questionDao.getQuestionList();
        } catch (QuestionsReadingException e) {
            localeReadWriteService.printLocale("file-not-found");
            return new ArrayList<>();
        } catch (Exception ex) {
            localeReadWriteService.printLocale("resource-error");
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
        if (userResult >= props.getPassMark()) {
            localeReadWriteService.printLocale("congrats", user.getFullName());
        } else {
            localeReadWriteService.printLocale("sorry", user.getFullName());
        }
        localeReadWriteService.printLocale("score", String.valueOf(userResult));
    }
}
