package br.ufrn.dimap.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtProcess extends DataProcess{
    @Override
    public List<List<String>> processedData(String pathToFile) throws FileNotFoundException {
        final String BREAK_POINT = "\n<<<[END_OF_FILE]>>>\n";
        File directoryPath = new File(pathToFile);
        File[] filesList = directoryPath.listFiles();
        Scanner scannedFile = null;
        StringBuilder mailStringBuilded = new StringBuilder();
        assert filesList != null;

        for(File file : filesList) {
            scannedFile = new Scanner(file);
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

        List<String> mails = new TxtProcess().tokienizerContent(mailStringBuilded.toString(),BREAK_POINT);
        List<List<String>> tokenizedMailsList = new ArrayList<>();

        for (String tokens : mails) {
            List<String> mailsTokens = new TxtProcess().tokienizerContent(tokens, " ");
            tokenizedMailsList.add(mailsTokens);
        }

        return tokenizedMailsList;
    }

}
