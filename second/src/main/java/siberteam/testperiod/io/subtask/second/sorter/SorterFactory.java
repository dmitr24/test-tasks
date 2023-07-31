package siberteam.testperiod.io.subtask.second.sorter;

import lombok.Getter;
import siberteam.testperiod.io.subtask.second.annotation.SorterInfo;
import siberteam.testperiod.io.subtask.second.data.SorterData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SorterFactory {
    private static final String PACKAGE_NAME = "siberteam/testperiod/io/subtask/second/sorter";

    @Getter
    private final Set<SorterData> sortersData;

    public SorterFactory() {
        this.sortersData = new HashSet<>();
    }

    public void build() {
        Set<Class<?>> sorters = loadClasses();
        if (sorters != null) {
            sorters.forEach(this::buildSorterData);
        }
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

    public Sorter getInstance(String className) throws InstantiationException, IllegalAccessException {
        for (SorterData sorter : sortersData) {
            if (sorter.getFullQualifiedClassName().equals(className)) {
                return (Sorter) sorter.getClassReference().newInstance();
            }
        }
        return null;
    }

    private Set<Class<?>> loadClasses() {
        try (InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(PACKAGE_NAME)) {
            if (stream == null) {
                return Collections.emptySet();
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                return reader.lines()
                        .filter(line -> line.endsWith(".class") && !line.startsWith("Sorter"))
                        .map(this::getClass)
                        .collect(Collectors.toSet());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Class<?> getClass(String className) {
        try {
            return Class.forName(PACKAGE_NAME.replace("/", ".") + "."
                    + className.replace(".class", ""));
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
            System.exit(1);
        }
        return null;
    }
}
