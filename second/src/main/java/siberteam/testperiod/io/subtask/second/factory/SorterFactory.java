package siberteam.testperiod.io.subtask.second.factory;

import siberteam.testperiod.io.subtask.second.annotation.SorterInfo;
import siberteam.testperiod.io.subtask.second.data.SorterData;
import siberteam.testperiod.io.subtask.second.sorter.Sorter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class SorterFactory {
    private static final String PACKAGE_NAME = "siberteam/testperiod/io/subtask/second/sorter";
    private final Map<String, SorterData> sorters = new HashMap<>();

    public SorterFactory() {
        Set<Class<?>> sorterClasses = loadClasses();
        sorterClasses.forEach(this::buildSorterData);
    }

    public Collection<SorterData> getSortersData() {
        return sorters.values();
    }

    private void buildSorterData(Class<?> sorterClass) {
        SorterData sorterData = new SorterData();
        sorterData.setFullQualifiedClassName(sorterClass.getName());
        if (sorterClass.getAnnotation(SorterInfo.class) != null) {
            sorterData.setName(sorterClass.getAnnotation(SorterInfo.class).name());
            sorterData.setDescription(sorterClass.getAnnotation(SorterInfo.class).description());
        }
        sorterData.setClassReference(sorterClass);
        sorters.put(sorterClass.getName(), sorterData);
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

    public Sorter getInstance(String sorterName) {
        try {
            return (Sorter) sorters.get(sorterName).getClassReference().newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            throw new RuntimeException("Unable to make instance of class - " + sorterName);
        }
    }
}
