package br.ufrn.dimap.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class CSVProcess extends DataProcess{
    private static final int BATCH_SIZE = 1000; // Tamanho do lote

    @Override
    public List<List<String>> processedData(String pathToFile) {
        List<List<String>> processedData = new ArrayList<>();
        StringBuilder mailStringBuilder = new StringBuilder();
        long counter = 0;

        try (Reader readerCSV = new FileReader(pathToFile);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(readerCSV)) {

            List<CSVRecord> batchRecords = new ArrayList<>(BATCH_SIZE);
            for (CSVRecord record : csvParser) {
                batchRecords.add(record);
                if (batchRecords.size() >= BATCH_SIZE) {
                    processBatch(batchRecords, mailStringBuilder);
                    counter += batchRecords.size();
                    batchRecords.clear();
                }
            }

            // Processar o restante dos registros que não se encaixam em um lote completo
            if (!batchRecords.isEmpty()) {
                processBatch(batchRecords, mailStringBuilder);
                counter += batchRecords.size();
            }

            System.out.println("Número de e-mails processados: " + counter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return processedData;
    }
}
