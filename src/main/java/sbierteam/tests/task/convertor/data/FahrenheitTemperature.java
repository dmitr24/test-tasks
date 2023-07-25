package sbierteam.tests.task.convertor.data;

public class FahrenheitTemperature extends Temperature {
    public FahrenheitTemperature(int value) {
        super(value);
    }

    @Override
    public String toString() {
        return value + "F";
    }
}
