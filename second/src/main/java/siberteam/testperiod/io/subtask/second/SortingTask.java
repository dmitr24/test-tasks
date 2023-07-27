package siberteam.testperiod.io.subtask.second;

import siberteam.testperiod.io.subtask.second.communicator.SecondTaskUserCommunicator;
import siberteam.testperiod.io.subtask.second.processor.TaskProcessor;

public class SortingTask {
    private static final String DEFAULT_LOCATION = "/home/dmitryk/projects/main/second/src/main/resources/second/";

    public static void main(String[] args) {
        SecondTaskUserCommunicator communicator = new SecondTaskUserCommunicator();
        String fileName = communicator.getValidFileNameFromUser(DEFAULT_LOCATION);
        String sorterClassName = communicator.getSorterClassNameFromUser();
        TaskProcessor taskProcessor = new TaskProcessor();
        taskProcessor.processTask(DEFAULT_LOCATION, fileName, sorterClassName);
    }
}
