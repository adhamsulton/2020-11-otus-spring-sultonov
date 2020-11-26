package ru.otus.homework1.service;

import ru.otus.homework1.Homework1Application;
import ru.otus.homework1.config.DataSource;
import ru.otus.homework1.domain.Answer;
import ru.otus.homework1.domain.Question;
import ru.otus.homework1.util.CsvParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionServiceImpl implements QuestionService {
    private final DataSource dataSource;
    private final CsvParser csvParser;

    public QuestionServiceImpl(DataSource dataSource, CsvParser csvParser) {
        this.dataSource = dataSource;
        this.csvParser = csvParser;
    }

    @Override
    public void printQuestions() {
        InputStream resourceAsStream = Homework1Application.class.getClassLoader().getResourceAsStream(dataSource.getFileName());
        if (resourceAsStream == null) {
            System.out.println("The corresponding test file was not found in the resources.");
            return;
        }

        Scanner scanner = new Scanner(resourceAsStream);
        List<Question> questionList = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            questionList.add(csvParser.parse(line));
        }

        System.out.println("Welcome to testing!");
        for (Question question : questionList) {
            System.out.println(question.getQuestionText());
            for (Answer answer : question.getAnswerList()) {
                System.out.println(answer.getAnswerText());
            }
        }
    }
}
