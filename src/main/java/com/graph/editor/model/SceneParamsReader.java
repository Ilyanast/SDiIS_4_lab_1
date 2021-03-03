package com.graph.editor.model;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SceneParamsReader {

    String filename;
    int [] sceneSizeParams;

    public SceneParamsReader(String filename) {
        this.filename = filename;
        readSceneSizeParams();
    }

    private void readSceneSizeParams() {
        sceneSizeParams = new int[2];
        Scanner scanner;

        try {
            scanner = new Scanner(new File("sceneParams.txt"));
            sceneSizeParams[0] = scanner.nextInt();
            sceneSizeParams[1] = scanner.nextInt();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found!");
        }
    }

    public  int[] getSceneSizeParams() {
        return sceneSizeParams;
    }

}
