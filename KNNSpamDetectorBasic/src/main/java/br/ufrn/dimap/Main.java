package br.ufrn.dimap;

import br.ufrn.dimap.data.CSVProcess;
import br.ufrn.dimap.data.TxtProcess;

import java.io.IOException;
import java.util.List;

public class Main extends CSVProcess {
    public static void main(String[] args) throws IOException {

        Main obj = new Main();
        obj.processedData("/home/ciceropaulino/Documents/knn_email_dataset/undefined/undefined_parsed.csv");

//        List<List<String>> test = obj.processedData("/home/ciceropaulino/Documents/knn_email_dataset/training/ham");
//        System.out.println("\n           List of Lists          ");
//        System.out.println("-------------------------------------");
//        test.forEach(innerList -> {
//            String line = String.join(", ", innerList);
//            System.out.print(line);
//        });
    }
}