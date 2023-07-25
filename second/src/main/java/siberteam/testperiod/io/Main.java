package siberteam.testperiod.io;

import siberteam.testperiod.io.subtask.first.Reader;

public class Main {
    public static void main(String[] args) {
        String res = Reader.readFromFile("example.txt");
        System.out.println(res);
    }
}