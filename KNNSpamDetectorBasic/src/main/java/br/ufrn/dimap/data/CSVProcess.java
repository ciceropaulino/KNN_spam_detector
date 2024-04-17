package br.ufrn.dimap.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class CSVProcess extends DataProcess{
    @Override
    public List<List<String>> processedData(String pathToFile) {
        String mailDatasetString = "";
        String mailBody = "";
        String mailBuffer = "";
        Reader readerCSV = null;
        long counter = 0;
        try {
            readerCSV = new FileReader(pathToFile);
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(readerCSV);
            List<CSVRecord> mailsRecords = csvParser.getRecords();
            for (CSVRecord records : mailsRecords) {
                mailBody = records.get("body_length");
                mailBuffer = new CSVProcess().mailDirtCleaner(mailBody.toLowerCase());
//                mailTokensList.add(new CSVProcess().tokienizerContent(mailBuffer.toLowerCase()," "));
                counter += 1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Mum emails " + counter);

        return /*new CSVProcess().dataOrganizer(mailStringBuilded.toString(),BREAK_POINT)*/ null;
    }
}
