package ru.kpfu.itis.kashapova.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import au.com.bytecode.opencsv.CSVReader;

public class ParseFullCSV {
    public Double[] dataset;
    public String path = "C:\\Users\\user\\IdeaProjects\\semesterWorkTV\\src\\ru\\kpfu\\itis\\kashapova\\resource\\";
    public String fileName;

    public ParseFullCSV(String fileName) {
        this.fileName = fileName;
    }

    @SuppressWarnings("resource")
    public Double[] parseFile() throws IOException {
        File file;
        file = new File(path + fileName);

        //Build reader instance
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(file), ',', '"', 1);
        } catch (FileNotFoundException e) {
            System.out.println("PFCSV# parse : can't found file");
        }

        List<String[]> allRows = reader.readAll();
        int i = 0;
        dataset = new Double[allRows.size()];
        for(String[] row : allRows){
            try {
                dataset[i] = Double.parseDouble(Arrays.toString(row).replaceAll("[\\[\\]]", ""));
                i++;
            } catch (IllegalArgumentException ex){
                //
            }
        }
        return dataset;
    }
}