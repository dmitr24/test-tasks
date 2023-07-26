package siberteam.testperiod.first;

import siberteam.testperiod.first.enums.TemperatureScale;
import siberteam.testperiod.first.task.convertor.CelsiusConvertor;
import siberteam.testperiod.first.task.convertor.FahrenheitConvertor;
import siberteam.testperiod.first.task.convertor.KelvinConvertor;
import siberteam.testperiod.first.task.convertor.data.CelsiusTemperature;
import siberteam.testperiod.first.task.convertor.data.FahrenheitTemperature;
import siberteam.testperiod.first.task.convertor.data.KelvinTemperature;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Пожалуйста, введите строку типа 400K=>F");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String temperature = line.split("=>")[0];
        TemperatureScale targetScale = TemperatureScale.valueOfLetter(line.split("=>")[1]);
        TemperatureScale actualScale =
                TemperatureScale.valueOfLetter(temperature.split("")[temperature.length() - 1]);

        switch (actualScale) {
            case KELVIN:
                int kelvinTempValue = extractNumber("K", temperature);
                KelvinTemperature kelvinTemperature = new KelvinTemperature(kelvinTempValue);
                handleKelvin(kelvinTemperature, targetScale);
                break;
            case FAHRENHEIT:
                int fahrenheitTempValue = extractNumber("F", temperature);
                FahrenheitTemperature fahrenheitTemperature = new FahrenheitTemperature(fahrenheitTempValue);
                handleFahrenheit(fahrenheitTemperature, targetScale);
                break;
            case CELSIUS:
                int celsiusTempValue = extractNumber("C", temperature);
                CelsiusTemperature celsiusTemperature = new CelsiusTemperature(celsiusTempValue);
                handleCelsius(celsiusTemperature, targetScale);
                break;
            default:
                System.err.println("Provided actual temperature scale is not supported");
                System.exit(1);
        }
    }

    private static void handleKelvin(KelvinTemperature kelvinTemperature,
                                     TemperatureScale targetTemperature) {
        switch (targetTemperature) {
            case KELVIN:
                System.out.println(kelvinTemperature);
                return;
            case FAHRENHEIT:
                System.out.println(FahrenheitConvertor.convert(kelvinTemperature));
                return;
            case CELSIUS:
                System.out.println(CelsiusConvertor.convert(kelvinTemperature));
            default:
                System.err.println("Provided target temperature scale is not supported");
                System.exit(1);
        }
    }

    private static void handleFahrenheit(FahrenheitTemperature fahrenheitTemperature,
                                         TemperatureScale targetTemperature) {
        switch (targetTemperature) {
            case KELVIN:
                System.out.println(KelvinConvertor.convert(fahrenheitTemperature));
                return;
            case FAHRENHEIT:
                System.out.println(targetTemperature);
                return;
            case CELSIUS:
                System.out.println(CelsiusConvertor.convert(fahrenheitTemperature));
            default:
                System.err.println("Provided target temperature scale is not supported");
                System.exit(1);
        }
    }

    private static void handleCelsius(CelsiusTemperature celsiusTemperature,
                                      TemperatureScale targetTemperature) {
        switch (targetTemperature) {
            case KELVIN:
                System.out.println(KelvinConvertor.convert(celsiusTemperature));
                return;
            case FAHRENHEIT:
                System.out.println(FahrenheitConvertor.convert(celsiusTemperature));
                return;
            case CELSIUS:
                System.out.println(celsiusTemperature);
            default:
                System.err.println("Provided actual temperature scale is not supported");
                System.exit(1);
        }
    }

    private static int extractNumber(String scale, String str) {
        return Integer.parseInt(str.replace(scale, ""));
    }
}