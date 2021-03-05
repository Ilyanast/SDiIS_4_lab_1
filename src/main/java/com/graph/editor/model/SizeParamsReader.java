package com.graph.editor.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SizeParamsReader {

    private int [] sizeParamsArray;
    private Scanner scanner;

    public SizeParamsReader() {
        readSizeParams();
    }

    //TODO Throw exception

    private void readSizeParams() {

        try {
            scanner = new Scanner(new File("src/main/resources/params/params.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File is not found!");
        }
        
        fillParamsArray();
    }

    private void fillParamsArray() {
        sizeParamsArray = new int[scanner.nextInt()];

        for (int i = 0; i < sizeParamsArray.length; i++){
            sizeParamsArray[i] = scanner.nextInt();
        }
    }


    public  int[] getAllParams() {
        return sizeParamsArray;
    }

}
