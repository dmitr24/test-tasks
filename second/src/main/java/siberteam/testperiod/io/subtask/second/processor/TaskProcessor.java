package siberteam.testperiod.io.subtask.second.processor;

import siberteam.testperiod.io.subtask.common.data.Text;
import siberteam.testperiod.io.subtask.common.io.FileReader;
import siberteam.testperiod.io.subtask.common.io.FileWriter;
import siberteam.testperiod.io.subtask.second.data.SortRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TaskProcessor {
    public void processTask(SortRequest sortRequest) {
        try {
            Path path = Paths.get(sortRequest.getFileName());
            FileReader reader = new FileReader(path);
            Text text = new Text(reader.readWholeFile());
            Class<?> sorter = Class.forName(sortRequest.getSorterName());
            Method invokableMethod = sorter
                    .getDeclaredMethod("sort", Text.class);
            Object result = invokableMethod.invoke(sorter.newInstance(), text);
            FileWriter writer = new FileWriter(sortRequest.getOutputDir());
            writer.write(result.toString());
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
}
