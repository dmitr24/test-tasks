package siberteam.testperiod.first.task.convertor;

import siberteam.testperiod.first.task.convertor.data.CelsiusTemperature;
import siberteam.testperiod.first.task.convertor.data.FahrenheitTemperature;
import siberteam.testperiod.first.task.convertor.data.KelvinTemperature;

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
