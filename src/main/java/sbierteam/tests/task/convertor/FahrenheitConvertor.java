package sbierteam.tests.task.convertor;

import sbierteam.tests.task.convertor.data.CelsiusTemperature;
import sbierteam.tests.task.convertor.data.FahrenheitTemperature;
import sbierteam.tests.task.convertor.data.KelvinTemperature;

public class FahrenheitConvertor {
    private FahrenheitConvertor() {}

    public static FahrenheitTemperature convert(CelsiusTemperature celsiusTemperature) {
        float k = (float) 9 / 5;
        int tempValue = (int) (celsiusTemperature.getValue() * k + 32);
        return new FahrenheitTemperature(tempValue);
    }

    public static FahrenheitTemperature convert(KelvinTemperature kelvinTemperature) {
        float k = (float) 9 / 5;
        int tempValue = (int) ((kelvinTemperature.getValue() - 273.15) * k + 32);
        return new FahrenheitTemperature(tempValue);
    }
}
