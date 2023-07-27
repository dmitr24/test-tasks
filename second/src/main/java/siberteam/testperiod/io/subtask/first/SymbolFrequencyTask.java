package siberteam.testperiod.io.subtask.first;

import siberteam.testperiod.io.subtask.first.communicator.FirstTaskUserCommunicator;
import siberteam.testperiod.io.subtask.first.processor.TaskProcessor;

public class SymbolFrequencyTask {
    private static final String DEFAULT_LOCATION = "/home/dmitryk/projects/main/second/src/main/resources/first/";

    public static void main(String[] args) {
        FirstTaskUserCommunicator communicator = new FirstTaskUserCommunicator();
        String fileName = communicator.getValidFileNameFromUser(DEFAULT_LOCATION);
        TaskProcessor taskProcessor = new TaskProcessor();
        taskProcessor.processTask(DEFAULT_LOCATION, fileName);
    }
}
