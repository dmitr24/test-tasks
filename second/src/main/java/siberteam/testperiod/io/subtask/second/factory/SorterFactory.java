package siberteam.testperiod.io.subtask.second.factory;

import lombok.Getter;
import siberteam.testperiod.io.subtask.second.annotation.SorterInfo;
import siberteam.testperiod.io.subtask.second.data.SorterData;
import siberteam.testperiod.io.subtask.second.sorter.Sorter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SorterFactory {
    private static final String PACKAGE_NAME = "siberteam/testperiod/io/subtask/second/sorter";

    @Getter
    private final Set<SorterData> sortersData = new HashSet<>();

    public SorterFactory() {
        Set<Class<?>> sorters = loadClasses();
        sorters.forEach(this::buildSorterData);
    }

    private void buildSorterData(Class<?> sorterClass) {
        SorterData sorterData = new SorterData();
        sorterData.setFullQualifiedClassName(sorterClass.getName());
        if (sorterClass.getAnnotation(SorterInfo.class) != null) {
            sorterData.setName(sorterClass.getAnnotation(SorterInfo.class).name());
            sorterData.setDescription(sorterClass.getAnnotation(SorterInfo.class).description());
        }
        sorterData.setClassReference(sorterClass);
        sortersData.add(sorterData);
    }

    public Sorter getInstance(String className) {
        for (SorterData sorter : sortersData) {
            if (sorter.getFullQualifiedClassName().equals(className)) {
                try {
                    return (Sorter) sorter.getClassReference().newInstance();
                } catch (InstantiationException | IllegalAccessException exception) {
                    throw new RuntimeException("Exception in sorter factory. Message: " +
                            exception.getMessage());
                }
            }
        }
        throw new RuntimeException("Sorter not found");
    }

    private Set<Class<?>> loadClasses() {
        try (InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(PACKAGE_NAME)) {
            if (stream == null) {
                return Collections.emptySet();
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                Set<Class<?>> classes = new HashSet<>();
                String line = reader.readLine();
                while (line != null) {
                    if (line.endsWith(".class") && !line.startsWith("Sorter")) {
                        classes.add(getClass(line));
                    }
                    line = reader.readLine();
                }
                return classes;
            }
        } catch (IOException exception) {
            throw new RuntimeException("Unable to get package filenames. Message: " + exception.getMessage());
        }
    }

    private Class<?> getClass(String className) {
        try {
            return Class.forName(PACKAGE_NAME.replace("/", ".") + "."
                    + className.replace(".class", ""));
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
            throw  new RuntimeException("Class not found: " + className);
        }
    }
}
