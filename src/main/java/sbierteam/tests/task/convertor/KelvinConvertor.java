package sbierteam.tests.task.convertor;

import sbierteam.tests.task.convertor.data.CelsiusTemperature;
import sbierteam.tests.task.convertor.data.FahrenheitTemperature;
import sbierteam.tests.task.convertor.data.KelvinTemperature;

public class KelvinConvertor {
    private KelvinConvertor() {}

    public static KelvinTemperature convert(CelsiusTemperature celsiusTemperature) {
        int tempValue =  (int) (celsiusTemperature.getValue() + 273.15);
        return new KelvinTemperature(tempValue);
    }

    public static KelvinTemperature convert(FahrenheitTemperature fahrenheitTemperature) {
        float k = (float) 5 / 9;
        int tempValue = (int) ((fahrenheitTemperature.getValue() - 32) * k + 273.15);
        return new KelvinTemperature(tempValue);
    }
}
