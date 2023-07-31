package siberteam.testperiod.io.subtask.second;

import org.apache.commons.cli.*;
import siberteam.testperiod.io.subtask.common.validator.FileValidator;
import siberteam.testperiod.io.subtask.second.cli.CliParser;
import siberteam.testperiod.io.subtask.second.data.SortRequest;
import siberteam.testperiod.io.subtask.second.processor.TaskProcessor;
import siberteam.testperiod.io.subtask.second.sorter.SorterFactory;

public class SortingTask {
    public static void main(String[] args) {
        try {
            SorterFactory sorterFactory = new SorterFactory();
            sorterFactory.build();
            CliParser parser = new CliParser();
            SortRequest sortRequest = parser.parse(args, sorterFactory);
            FileValidator validator = new FileValidator();
            if (!validator.validate(sortRequest.getFileName())) {
                System.err.println("File with such name not found");
                System.exit(1);
            }
            TaskProcessor taskProcessor = new TaskProcessor();
            taskProcessor.processTask(sortRequest, sorterFactory);
        } catch (ParseException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
