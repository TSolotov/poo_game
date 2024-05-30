package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFile {
    String fileName;

    public CSVFile(String fileName) {
        this.fileName = fileName;
        createCSV(fileName);
    }

    private void createCSV(String fileName) {
        File file = new File(fileName);
        if (file.exists())
            return;

        // * Crea el CSV (No le agrego un header xq da problemas a la hora de sort)
        try (FileWriter writer = new FileWriter(fileName)) {
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeLine(FileWriter writer, String[] row) throws IOException {
        for (int i = 0; i < row.length; i++) {
            writer.append(row[i]);
            if (i < row.length - 1) {
                writer.append(",");
            }
        }
        writer.append("\n");
    }

    public ArrayList<String[]> readCSV() {
        ArrayList<String[]> allData = new ArrayList<>();
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                allData.addFirst(data);
            }
            return allData;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getFileName() {
        return fileName;
    }
}