package com.graph.editor.controller;

import com.graph.editor.model.Graph;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;

public class LoadGraphFromFile {

    private final Graph graph;
    private final Pane pane;

    public LoadGraphFromFile(Graph graph, Pane pane) {
        this.graph = graph;
        this.pane = pane;

        handleLoadGraphFromFile(getChosenFile());
    }

    private void handleLoadGraphFromFile(File chosenFile) {
        pane.getChildren().clear();
        graph.clear();
    }

    private File getChosenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose input file");
        return fileChooser.showOpenDialog(null);
    }
}