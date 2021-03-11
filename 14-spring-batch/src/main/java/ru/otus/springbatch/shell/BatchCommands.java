package ru.otus.springbatch.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {

    private final Job importBookJob;

    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;
    private final JobExplorer jobExplorer;

    //http://localhost:8080/h2-console/

    @ShellMethod(value = "startMigrationJobFromH2ToMongo", key = "sj")
    public void startMigrationJobFromH2ToMongo() throws Exception {
        JobExecution execution = jobLauncher.run(importBookJob, new JobParametersBuilder()
//                .addString(INPUT_FILE_NAME, appProps.getInputFile())
//                .addString(OUTPUT_FILE_NAME, appProps.getOutputFile())
                .toJobParameters());
        System.out.println(execution);
    }
}
