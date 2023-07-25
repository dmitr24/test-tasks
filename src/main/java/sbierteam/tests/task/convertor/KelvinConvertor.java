package sbierteam.tests.task.convertor;

public class KelvinConvertor implements Convertor {
    @Override
    public String convert(String convertable) {
        String from = convertable.split("")[convertable.length() - 1];
        switch (from) {
            case "C":
                return convertFromCelsius(convertable);
            case "F":
                return convertFromFahrenheit(convertable);
            case "K":
                return convertable;
            default:
                return null;
        }
    }

    private String convertFromCelsius(String str) {
        int celsiusValue = Integer.parseInt(str.replace("C", ""));
        return (int) (celsiusValue + 273.15) + "K";
    }

    private String convertFromFahrenheit(String str) {
        int fahrenheitValue = Integer.parseInt(str.replace("F", ""));
        float k = (float) 5 / 9;
        return (int) ((fahrenheitValue - 32) * k + 273.15) + "K";
    }
}
