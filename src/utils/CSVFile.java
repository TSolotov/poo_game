package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFile {
    private static String fileNameCircus = "dataCircus.csv";

    public CSVFile() {
        createCSV();
    }

    private void createCSV() {
        File file = new File(fileNameCircus);
        if (file.exists())
            return;

        // * Crea el CSV (No le agrego un header xq da problemas a la hora de sort)
        try (FileWriter writer = new FileWriter(fileNameCircus);) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLine(FileWriter writer, String[] row) throws IOException {
        for (int i = 0; i < row.length; i++) {
            writer.append(row[i]);
            if (i < row.length - 1) {
                writer.append(",");
            }
        }
        writer.append("\n");
    }

    public ArrayList<String[]> readCSV(String fileNameCircus) {
        ArrayList<String[]> allData = new ArrayList<>();
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(fileNameCircus))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                allData.add(data);
            }
            return allData;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getFileNameCircus() {
        return fileNameCircus;
    }
}