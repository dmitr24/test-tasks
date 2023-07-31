package siberteam.testperiod.io.subtask.second.processor;

import siberteam.testperiod.io.subtask.common.data.Text;
import siberteam.testperiod.io.subtask.common.io.FileReader;
import siberteam.testperiod.io.subtask.common.io.FileWriter;
import siberteam.testperiod.io.subtask.second.data.SortRequest;
import siberteam.testperiod.io.subtask.second.parser.StreamTextParser;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class TaskProcessor {
    public void processTask(SortRequest sortRequest) {
        try {
            List<String> words = getWords(sortRequest.getFileName());
            Object result = sort(sortRequest.getSorterName(), words);
            write(sortRequest.getOutputDir(), result.toString());
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

    private List<String> getWords(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        FileReader fileReader = new FileReader(path);
        try (Stream<String> lines = fileReader.getNotClosedLinesStream()) {
            StreamTextParser streamTextParser = new StreamTextParser(lines);
            return streamTextParser.getDictionary();
        }
    }

    private Object sort(String sorterName, List<String> words) throws NoSuchMethodException,
            ClassNotFoundException, InstantiationException, IllegalAccessException,
            InvocationTargetException {
        Text text = new Text(words);
        Class<?> sorter = Class.forName(sorterName);
        Method invokableMethod = sorter
                .getDeclaredMethod("sort", Text.class);
        return invokableMethod.invoke(sorter.newInstance(), text);
    }

    private void write(String outputDir, String text) throws IOException {
        FileWriter writer = new FileWriter(outputDir);
        writer.write(text);
    }
}
