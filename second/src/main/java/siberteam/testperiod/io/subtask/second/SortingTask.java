package siberteam.testperiod.io.subtask.second;

import org.apache.commons.cli.*;
import siberteam.testperiod.io.subtask.common.validator.FileValidator;
import siberteam.testperiod.io.subtask.second.cli.CliParser;
import siberteam.testperiod.io.subtask.second.data.SortRequest;
import siberteam.testperiod.io.subtask.second.processor.TaskProcessor;

public class SortingTask {
    public static void main(String[] args) {
        try {
            CliParser parser = new CliParser();
            SortRequest sortRequest = parser.parse(args);
            FileValidator validator = new FileValidator();
            if (!validator.validate(sortRequest.getFileName())) {
                System.err.println("File with such name not found");
                System.exit(1);
            }
            TaskProcessor taskProcessor = new TaskProcessor();
            taskProcessor.processTask(sortRequest);
        } catch (ParseException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
