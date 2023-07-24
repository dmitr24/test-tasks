package org.example.tak.convertor;

public class CelsiusConvertor implements Convertor{
    @Override
    public String convert(String convertable) {
        String from = convertable.split("")[convertable.length() - 1];
        switch (from) {
            case "K":
                return convertFromKelvins(convertable);
            case "F":
                return convertFromFahrenheit(convertable);
        }

        return null;
    }

    private String convertFromKelvins(String str) {
        int kelvinValue = Integer.parseInt(str.replace("K", ""));

        return (int) (kelvinValue - 273.15) + "C";
    }

    private String convertFromFahrenheit(String str) {
        int fahrValue = Integer.parseInt(str.replace("F", ""));
        float k = (float) 5 / 9;
        return (int) (k * (fahrValue - 32)) + "C";
    }
}
