package br.ufrn.dimap.data;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public abstract class DataProcess {
    public DataProcess() {

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
                "\"", "$", ";", "?", "!", "\\", "|", "{", "}", "[", "]",
                "'", ">", "<", "*", "/", "0", "1", "2", "3", "4", "5", "6",
                "7", "8", "9", "_", "\n"
        ));

        for (String dirt : dirtStr) {
            if (mail.contains(dirt)) {
                mail = mail.replace(dirt, "");
            }
        }
        //replace concatenade spaces per a single space
        mail = mail.trim().replaceAll(" +", " ");

        return mail;
    }
    protected List<List<String>> dataOrganizer(String mailStringBuilded, String breakPoint) {
        List<String> mails = new TxtProcess().tokienizerContent(mailStringBuilded, breakPoint);
        List<List<String>> tokenizedMailsList = new Vector<>();

        for (String tokens : mails) {
        List<String> mailsTokens = tokienizerContent(tokens, " ");
            tokenizedMailsList.add(mailsTokens);
        }

        return tokenizedMailsList;
    }
    protected abstract List<List<String>> processedData(String pathToFile) throws FileNotFoundException;

    }
