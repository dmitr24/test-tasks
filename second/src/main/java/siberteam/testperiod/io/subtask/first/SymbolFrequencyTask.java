package siberteam.testperiod.io.subtask.first;

import siberteam.testperiod.io.subtask.first.domain.Analyzer;
import siberteam.testperiod.io.subtask.first.io.Reader;
import siberteam.testperiod.io.subtask.first.io.Writer;
import siberteam.testperiod.io.subtask.first.util.HistogramUtils;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class SymbolFrequencyTask {
    public static void main(String[] args) {
        try {
            System.err.println("If trimmed string doesn't have signs, filename automatically sets to example-1.txt\n");
            System.out.print("Please, enter filename which you want to analyze (e.g. example-2.txt): ");
            Scanner scanner = new Scanner(System.in);
            String fileName = scanner.nextLine();
            if (fileName.trim().length() == 0) {
                fileName = "example-1.txt";
            }
            String text = Reader.readFromFile(fileName);
            Map<String, Float> histogramData =  Analyzer.analyze(text);
            String output = HistogramUtils.parseToText(histogramData);
            Writer.write(output);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
            System.exit(1);
        }
    }
}
