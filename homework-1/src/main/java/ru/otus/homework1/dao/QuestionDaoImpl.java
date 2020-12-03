package ru.otus.homework1.dao;

import ru.otus.homework1.config.DataSource;
import ru.otus.homework1.domain.Question;
import ru.otus.homework1.util.CsvParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionDaoImpl implements QuestionDao {
    private final DataSource dataSource;
    private final CsvParser csvParser;

    public QuestionDaoImpl(DataSource dataSource, CsvParser csvParser) {
        this.dataSource = dataSource;
        this.csvParser = csvParser;
    }

    @Override
    public List<Question> getQuestionList() throws IOException {
        List<Question> questionList = new ArrayList<>();
        try (InputStream resourceAsStream = QuestionDaoImpl.class.getClassLoader().getResourceAsStream(dataSource.getFileName())) {
            if (resourceAsStream == null) {
                throw new FileNotFoundException();
            }

            try (Scanner scanner = new Scanner(resourceAsStream)) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    questionList.add(csvParser.parse(line));
                }
            }
        }

        return questionList;
    }
}
