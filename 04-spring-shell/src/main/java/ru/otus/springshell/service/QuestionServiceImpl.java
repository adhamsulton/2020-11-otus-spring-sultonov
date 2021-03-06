package ru.otus.springshell.service;

import org.springframework.stereotype.Service;
import ru.otus.springshell.config.AppProps;
import ru.otus.springshell.dao.QuestionDao;
import ru.otus.springshell.domain.Answer;
import ru.otus.springshell.domain.Question;
import ru.otus.springshell.domain.User;
import ru.otus.springshell.exception.QuestionsReadingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;
    private final ReadWriteService readWriteService;
    private final LocaleReadWriteService localeReadWriteService;
    private final AppProps props;

    public QuestionServiceImpl(QuestionDao questionDao, ReadWriteService readWriteService,
                               LocaleReadWriteService localeReadWriteService, AppProps props) {
        this.questionDao = questionDao;
        this.readWriteService = readWriteService;
        this.localeReadWriteService = localeReadWriteService;
        this.props = props;
    }

    @Override
    public void runTest(User user) {
        List<Question> questionList = getQuestions();

        if (user == null) return;

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
