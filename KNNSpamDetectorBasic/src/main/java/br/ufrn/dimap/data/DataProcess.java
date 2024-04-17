package br.ufrn.dimap.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public abstract class DataProcess {
    protected final String BREAK_POINT;

    protected DataProcess() {
        BREAK_POINT = "\n<<<[END_OF_FILE]>>>\n";
    }

    protected List<String> tokienizerContent(String mail, String breakPoint) {
        List<String> tokens = new Vector<>();
        StringTokenizer tokienizer = new StringTokenizer(mail, breakPoint);
        while (tokienizer.hasMoreTokens()) {
            tokens.add(tokienizer.nextToken());
        }

        return tokens;
    }
    protected String mailDirtCleaner(String mail) {
        List<String> dirtStr = Collections.unmodifiableList(Arrays.asList(
                ":", ",", ".", "@", "#", "%", "&", "(", ")", "-", "+",
                "\"", "$", ";", "~", "?", "!", "\\", "|", "{", "}", "[", "]",
                "'", ">", "<", "*", "/", "0", "1", "2", "3", "4", "5", "6",
                "7", "8", "9", "_", "=", "\n", "\r", "\r\n", "\t"
        ));

        for (String dirt : dirtStr) {
            if (mail.contains(dirt)) {
                mail = mail.replace(dirt, " ");
            }
        }
        //replace concatenade caracters per a single space
        mail = mail.trim().replaceAll(" +", " ");

        return mail;
    }
    protected List<List<String>> dataOrganizer(String mailString, String breakPoint) {
        List<String> mails = new TxtProcess().tokienizerContent(mailString, breakPoint);
        List<List<String>> tokenizedMailsList = new Vector<>();

        for (String tokens : mails) {
        List<String> mailsTokens = tokienizerContent(tokens, " ");
            tokenizedMailsList.add(mailsTokens);
        }

        return tokenizedMailsList;
    }

    protected void processBatch(List<CSVRecord> batchRecords, StringBuilder mailStringBuilder) {
        for (CSVRecord record : batchRecords) {
            String mailBody = record.get("body");
            String mailBuffer = mailDirtCleaner(mailBody.toLowerCase());
            // Adicione aqui qualquer processamento adicional necessário
            mailStringBuilder.append(mailBuffer).append("\n");
        }
    }

    protected abstract List<List<String>> processedData(String pathToFile);

    }
