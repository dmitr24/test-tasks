package siberteam.testperiod.first.task.convertor.data;

public class KelvinTemperature extends Temperature {
    public KelvinTemperature(int value) {
        super(value);
    }

    @Override
    public String toString() {
        return value + "K";
    }
}
