package siberteam.testperiod.mt2.second.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SorterData {
    private String fullQualifiedClassName;
    private String name;
    private String description;
    private Class<?> classReference;

    @Override
    public String toString() {
        return "-----------------------\n" +
                "Sorter name: " + (name != null ? name : "not specified") + "\n" +
                "Classname: " + fullQualifiedClassName + "\n" +
                "Description: " + (description != null ? description : "not specified") + "\n" +
                "-----------------------\n";
    }
}
