package sbierteam.tests.task.convertor;

import sbierteam.tests.task.convertor.data.CelsiusTemperature;
import sbierteam.tests.task.convertor.data.FahrenheitTemperature;
import sbierteam.tests.task.convertor.data.KelvinTemperature;

public class CelsiusConvertor  {
    private CelsiusConvertor() {}

    public static CelsiusTemperature convert(KelvinTemperature kelvinTemperature) {
        int tempValue = (int) (kelvinTemperature.getValue() - 273.15);
        return new CelsiusTemperature(tempValue);
    }

    public static CelsiusTemperature convert(FahrenheitTemperature fahrenheitTemperature) {
        float k = (float) 5 / 9;
        int tempValue = (int) (k * (fahrenheitTemperature.getValue() - 32));
        return new CelsiusTemperature(tempValue);
    }
}