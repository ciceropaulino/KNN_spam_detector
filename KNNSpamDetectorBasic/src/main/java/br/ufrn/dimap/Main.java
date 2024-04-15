package br.ufrn.dimap;

import br.ufrn.dimap.data.TxtProcess;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main extends TxtProcess {
    public static void main(String[] args) throws IOException {

        Main obj = new Main();

        obj.processedData("/home/ciceropaulino/Documents/knn_email_dataset/training/ham");
    }
}