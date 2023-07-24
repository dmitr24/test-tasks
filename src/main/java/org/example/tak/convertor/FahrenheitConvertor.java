package org.example.tak.convertor;

public class FahrenheitConvertor implements Convertor {
    @Override
    public String convert(String convertable) {
        String from = convertable.split("")[convertable.length() - 1];
        switch (from) {
            case "C":
                return convertFromCelsius(convertable);
            case "K":
                return convertFromKelvins(convertable);
            case "F":
                return convertable;
        }

        return null;
    }

    private String convertFromCelsius(String str) {
        int celsiusValue = Integer.parseInt(str.replace("C", ""));
        float k = (float) 9 / 5;

        return (int) (celsiusValue * k + 32) + "F";
    }

    private String convertFromKelvins(String str) {
        int kelvinValue = Integer.parseInt(str.replace("K", ""));
        float k = (float) 9 / 5;
        return (int) ((kelvinValue - 273.15) * k + 32) + "F";
    }
}
