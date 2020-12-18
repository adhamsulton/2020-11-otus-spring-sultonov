package ru.otus.homework4.dao;

import org.springframework.stereotype.Repository;
import ru.otus.homework4.config.DataSource;
import ru.otus.homework4.domain.Question;
import ru.otus.homework4.exception.QuestionsReadingException;
import ru.otus.homework4.util.CsvParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
public class QuestionDaoImpl implements QuestionDao {
    private final DataSource dataSource;
    private final CsvParser csvParser;

    public QuestionDaoImpl(DataSource dataSource, CsvParser csvParser) {
        this.dataSource = dataSource;
        this.csvParser = csvParser;
    }

    @Override
    public List<Question> getQuestionList() throws QuestionsReadingException {
        List<Question> questionList = new ArrayList<>();
        try (InputStream resourceAsStream = QuestionDaoImpl.class.getClassLoader().getResourceAsStream(dataSource.getFileName())) {
            if (resourceAsStream == null) {
                throw new QuestionsReadingException("file not found");
            }

            try (Scanner scanner = new Scanner(resourceAsStream)) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    questionList.add(csvParser.parse(line));
                }
            }
        } catch (IOException e) {
            throw new QuestionsReadingException("error reading file", e);
        }

        return questionList;
    }
}
