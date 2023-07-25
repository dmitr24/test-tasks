package sbierteam.tests;

import sbierteam.tests.enums.TemperatureScale;
import sbierteam.tests.task.convertor.CelsiusConvertor;
import sbierteam.tests.task.convertor.FahrenheitConvertor;
import sbierteam.tests.task.convertor.KelvinConvertor;
import sbierteam.tests.task.convertor.data.CelsiusTemperature;
import sbierteam.tests.task.convertor.data.FahrenheitTemperature;
import sbierteam.tests.task.convertor.data.KelvinTemperature;
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
        }
    }

    private static int extractNumber(String scale, String str) {
        return Integer.parseInt(str.replace(scale, ""));
    }
}