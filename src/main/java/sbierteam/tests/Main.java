package sbierteam.tests;

import sbierteam.tests.tak.convertor.CelsiusConvertor;
import sbierteam.tests.tak.convertor.Convertor;
import sbierteam.tests.tak.convertor.FahrenheitConvertor;
import sbierteam.tests.tak.convertor.KelvinConvertor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Пожалуйста, введите строку типа 400K F");
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        String value = line.split(" ")[0];
        String scale = line.split(" ")[1];

        Convertor convertor;
        String result;

        switch (scale) {
            case "K":
                convertor = new KelvinConvertor();
                result = convertor.convert(value);
                break;
            case "F":
                convertor = new FahrenheitConvertor();
                result = convertor.convert(value);
                break;
            case "C":
                convertor = new CelsiusConvertor();
                result = convertor.convert(value);
                break;
            default:
                result = "Wrong scale specified";
        }

        System.out.println(result);
    }
}