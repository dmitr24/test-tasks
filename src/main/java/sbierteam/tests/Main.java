package sbierteam.tests;

import sbierteam.tests.task.convertor.CelsiusConvertor;
import sbierteam.tests.task.convertor.Convertor;
import sbierteam.tests.task.convertor.FahrenheitConvertor;
import sbierteam.tests.task.convertor.KelvinConvertor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Пожалуйста, введите строку типа 400K F");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String value = line.split(" ")[0];
        String scale = line.split(" ")[1];
        String result;
        Convertor convertor;

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