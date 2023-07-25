package siberteam.testperiod.first.enums;

public enum TemperatureScale {
    KELVIN("K"),
    FAHRENHEIT("F"),
    CELSIUS("C");

    private final String letter;

    TemperatureScale(String letter) {
        this.letter = letter;
    }

    public static TemperatureScale valueOfLetter(String temp) {
        for (TemperatureScale temperatureScale : values()) {
            if (temperatureScale.letter.equals(temp)) {
                return temperatureScale;
            }
        }
        return null;
    }
}
