package ru.otus.homework1.service;

import ru.otus.homework1.dao.QuestionDao;
import ru.otus.homework1.domain.Answer;
import ru.otus.homework1.domain.Question;

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
        } catch (Exception e) {
            printService.print(e.getMessage());
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
