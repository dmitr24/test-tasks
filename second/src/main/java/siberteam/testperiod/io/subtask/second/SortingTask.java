package siberteam.testperiod.io.subtask.second;

import siberteam.testperiod.io.subtask.second.cli.CliParser;
import siberteam.testperiod.io.subtask.second.data.request.HelpRequest;
import siberteam.testperiod.io.subtask.second.data.request.SortRequest;
import siberteam.testperiod.io.subtask.second.data.request.UserRequest;
import siberteam.testperiod.io.subtask.second.exception.*;
import siberteam.testperiod.io.subtask.second.handler.HelpRequestHandler;
import siberteam.testperiod.io.subtask.second.handler.SortRequestHandler;
import siberteam.testperiod.io.subtask.second.factory.SorterFactory;
import siberteam.testperiod.io.subtask.second.io.FileReader;
import siberteam.testperiod.io.subtask.second.io.FileWriter;

public class SortingTask {
    public static void main(String[] args) {
        try {
            CliParser parser = new CliParser();
            UserRequest userRequest = parser.parse(args);
            SorterFactory sorterFactory = new SorterFactory();
            if (userRequest instanceof HelpRequest) {
                HelpRequestHandler helpRequestHandler = new HelpRequestHandler(sorterFactory);
                helpRequestHandler.handle();
            } else if (userRequest instanceof SortRequest) {
                SortRequest sortRequest = (SortRequest) userRequest;
                FileReader fileReader = new FileReader(sortRequest.getFileName());
                FileWriter fileWriter = new FileWriter(sortRequest.getOutputDir());
                SortRequestHandler sortRequestHandler =
                        new SortRequestHandler(fileReader, sorterFactory, fileWriter);
                sortRequestHandler.handle(sortRequest);
            } else {
                System.err.println("Unexpected user request occurred");
                System.exit(1);
            }
        } catch (ParserException | ValidationException | SorterFactoryException |
                 WriterException | ReaderException exception) {
            System.err.println(exception.getMessage());
            System.exit(1);
        }
    }
}
