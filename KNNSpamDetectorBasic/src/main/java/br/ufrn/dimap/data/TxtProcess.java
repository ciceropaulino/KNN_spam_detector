package br.ufrn.dimap.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class TxtProcess extends DataProcess{
    @Override
    public List<List<String>> processedData(String pathToFile) throws FileNotFoundException {
        final String BREAK_POINT = "\n<<<[END_OF_FILE]>>>\n";
        File directoryPath = new File(pathToFile);
        File[] filesList = directoryPath.listFiles();
        Scanner sc = null;
        assert filesList != null;

        for(File file : filesList) {
            sc= new Scanner(file);
            String nextMailLine = "";
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                nextMailLine = sc.nextLine();
                nextMailLine = new TxtProcess().mailDirtCleaner(nextMailLine);
                sb.append(nextMailLine).append(" ");
                if (!sc.hasNextLine()) {sb.append(nextMailLine).append(BREAK_POINT);}
            }
            //TxtProcess clearMail = new TxtProcess();
            //String cleannedMail = clearMail.mailDirtCleaner(sb.toString());
            List<String> mailTokens = new TxtProcess().tokienizerContent(sb.toString(),BREAK_POINT);
            for (String tokens : mailTokens) {
                //String cleannedMail = new TxtProcess().mailDirtCleaner(tokens);
                System.out.println("Contents of the file: \n" +tokens);
            }

        }

        return null;
    }

}
