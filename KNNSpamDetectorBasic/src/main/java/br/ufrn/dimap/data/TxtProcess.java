package br.ufrn.dimap.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class TxtProcess extends DataProcess{
    @Override
    public Vector<Vector<String>> processedData(String pathToFile) throws FileNotFoundException {
        final String BREAK_POINT = "<<<[END OF FILE]>>>";
        File directoryPath = new File(pathToFile);
        File[] filesList = directoryPath.listFiles();
        Scanner sc = null;
        assert filesList != null;
        for(File file : filesList) {
            sc= new Scanner(file);
            String input = "";
            //inicializar variavel aqui
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                sb.append(input).append(" ");
                //if (!sc.hasNextLine()) {sb.append(input).append("   END OF FILE   ");}
            }
            TxtProcess clearMail = new TxtProcess();
            String cleannedMail = clearMail.mailDirtCleaner(sb.toString());
            sb.append(input).append(BREAK_POINT);
            System.out.println("\nContents of the file: \n" +cleannedMail);
        }

        return null;
    }

}
