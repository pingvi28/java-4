package ru.kpfu.itis.kashapova.java;

import java.io.IOException;

public class App {
    public static Functional functional;

    public static void main(String[] args) {
        System.out.println("Запуск прпиложения");
        ParseFullCSV parseFullCSV = new ParseFullCSV("dataset3.csv");
        try {
            functional = new Functional(parseFullCSV.parseFile());
            functional.start();
        } catch (IOException e) {
            System.out.println("App#main: can't parse");
        }

        functional.writeAll();
        functional.drawHistogram();
    }
}
