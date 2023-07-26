package siberteam.testperiod.io.subtask.second;

import siberteam.testperiod.io.subtask.common.util.ConsoleUtils;
import siberteam.testperiod.io.subtask.common.io.Reader;
import siberteam.testperiod.io.subtask.common.io.Writer;
import siberteam.testperiod.io.subtask.second.data.Task;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class SortingTask {
    private static final String DEFAULT_LOCATION = "/home/dmitryk/projects/main/second/src/main/resources/second/";

    public static void main(String[] args) {
        Task taskDto = getTaskDtoFromUser();
        processTask(taskDto);
    }

    private static void processTask(Task taskDto) {
        try {
            Reader.setLocation(DEFAULT_LOCATION);
            String text = Reader.readFromFile(taskDto.getFileName());
            Class<?> sorter = Class.forName(taskDto.getSorterClasName());
            Method invokableMethod = sorter
                    .getDeclaredMethod("sort", String.class);
            Object result = invokableMethod.invoke(sorter.newInstance(), text);
            Writer.setLocation(DEFAULT_LOCATION);
            Writer.write(result.toString());
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("Class with such name not found");
            System.exit(1);
        } catch (NoSuchMethodException e) {
            System.err.println("This class doesn't have compatible sort method");
            System.exit(1);
        } catch (InvocationTargetException e) {
            System.err.println("Unable to call sort method");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.err.println("Access to sort method denied");
            System.exit(1);
        } catch (InstantiationException e) {
            System.err.println("Can't create instance of sorter class");
            System.exit(1);
        }
    }

    private static Task getTaskDtoFromUser() {
        ConsoleUtils.printLnGreen("If trimmed string doesn't have signs," +
                " filename automatically sets to example-1.txt for faster checkup\n");
        System.out.print("Please, enter name of file which you want to sort (e.g. example-2.txt): ");
        Scanner scanner = new Scanner(System.in);
        Task taskDto = new Task();
        taskDto.setFileName(scanner.nextLine());
        if (taskDto.getFileName().trim().length() == 0) {
            taskDto.setFileName("example-1.txt");
        }
        System.out.print("Please, enter full qualified classname of sorter that you chose: ");
        taskDto.setSorterClasName(scanner.nextLine());

        return taskDto;
    }
}
