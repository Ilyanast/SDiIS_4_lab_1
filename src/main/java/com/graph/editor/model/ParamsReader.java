package com.graph.editor.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParamsReader {

    private int [] sizeParams;
    private Scanner scanner;

    public ParamsReader(String filename) {
        readSizeParams(filename);
        
    }

    private void readSizeParams(String filename) {

        try {
            scanner = new Scanner(new File("params.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File is not found!");
        }
        
        fillParamsArray();
    }

    private void fillParamsArray() {
        sizeParams = new int[scanner.nextInt()];

        for (int i = 0; i < sizeParams.length; i++){
            sizeParams[i] = scanner.nextInt();
        }
    }


    public  int[] getAllParams() {
        return sizeParams;
    }

}
