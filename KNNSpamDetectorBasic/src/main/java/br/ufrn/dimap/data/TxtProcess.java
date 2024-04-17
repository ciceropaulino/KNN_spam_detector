package br.ufrn.dimap.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class TxtProcess extends DataProcess{
    @Override
    public List<List<String>> processedData(String pathToFile) {
        File directoryPath = new File(pathToFile);
        File[] filesList = directoryPath.listFiles();
        Scanner scannedFile = null;
        StringBuilder mailStringBuilded = new StringBuilder();
        assert filesList != null;

        for(File file : filesList) {
            try {
                scannedFile = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            String nextMailLine = "";
            while (scannedFile.hasNextLine()) {
                nextMailLine = scannedFile.nextLine();
                nextMailLine = new TxtProcess().mailDirtCleaner(nextMailLine);
                mailStringBuilded.append(nextMailLine).append(" ");
                if (!scannedFile.hasNextLine()) {
                    mailStringBuilded.append(nextMailLine).append(BREAK_POINT);
                }
            }
        }
        return new TxtProcess().dataOrganizer(mailStringBuilded.toString(),BREAK_POINT);
    }
}
