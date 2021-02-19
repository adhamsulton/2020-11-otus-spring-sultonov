package ru.otus.xmlconfiguration.service;

import ru.otus.xmlconfiguration.dao.QuestionDao;
import ru.otus.xmlconfiguration.domain.Answer;
import ru.otus.xmlconfiguration.domain.Question;
import ru.otus.xmlconfiguration.exception.QuestionsReadingException;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;
    private final PrintService printService;

    public QuestionServiceImpl(QuestionDao questionDao, PrintService printService) {
        this.questionDao = questionDao;
        this.printService = printService;
    }

    @Override
    public void printQuestions() {
        List<Question> questionList = null;
        try {
            questionList = questionDao.getQuestionList();
        } catch (QuestionsReadingException e) {
            printService.print("The corresponding test file was not found in the resources.");
        } catch (Exception ex) {
            printService.print("Unexpected error by getting resource");
        }

        printService.print("Welcome to testing!");

        for (Question question : questionList) {
            printService.print(question.getQuestionText());
            for (Answer answer : question.getAnswerList()) {
                printService.print(answer.getAnswerText());
            }
        }
    }
}
