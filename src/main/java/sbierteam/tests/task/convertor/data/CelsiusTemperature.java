package sbierteam.tests.task.convertor.data;

public class CelsiusTemperature extends Temperature {
    public CelsiusTemperature(int value) {
        super(value);
    }

    @Override
    public String toString() {
        return value + "C";
    }
}
