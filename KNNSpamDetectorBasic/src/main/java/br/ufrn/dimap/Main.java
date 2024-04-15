package br.ufrn.dimap;

import br.ufrn.dimap.data.TxtProcess;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main extends TxtProcess {
    public static void main(String[] args) throws IOException {

        Main obj = new Main();

        List<List<String>> test = obj.processedData("/home/ciceropaulino/Documents/knn_email_dataset/training/ham");
        System.out.println("\n           List of Lists          ");
        System.out.println("-------------------------------------");
        test.forEach(innerList -> {
            String line = String.join(", ", innerList);
            System.out.print(line);
        });
    }
}