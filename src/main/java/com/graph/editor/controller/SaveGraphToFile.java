package com.graph.editor.controller;

import com.graph.editor.model.Graph;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGraphToFile {

    private final MainController mainController;

    public SaveGraphToFile(MainController mainController) {
        this.mainController = mainController;

        handleSaveGraphToFile();
    }

    private void handleSaveGraphToFile() {
        try {
            FileWriter fileWriter = new FileWriter(getChosenFile());
            pushInfo(fileWriter);
            pushAllVertices(fileWriter);
            pushAllEdges(fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pushInfo(FileWriter fileWriter) throws IOException {
        fileWriter.write(String.valueOf(mainController.getGraph().getGraphList().size()));
        fileWriter.append('\n');
        fileWriter.write(String.valueOf(mainController.getGraph().getEdgeList().size()));
        fileWriter.append('\n');
    }

    private void pushAllVertices(FileWriter fileWriter) throws IOException {
        Graph graph = mainController.getGraph();

        int amountOfVertices = graph.getGraphList().size();

        for (int i = 0; i < amountOfVertices; ++i) {
            fileWriter.write(String.valueOf( (int) graph.getGraphList().get(i).getVertex().getCircleCenterX()));
            fileWriter.append(' ');
            fileWriter.write(String.valueOf( (int) graph.getGraphList().get(i).getVertex().getCircleCenterY()));
            fileWriter.append(' ');
            fileWriter.write(graph.getGraphList().get(i).getVertex().getIdentifier());
            fileWriter.append('\n');
        }
    }

    private void pushAllEdges(FileWriter fileWriter) throws IOException {

        Graph graph = mainController.getGraph();

        int amountOfEdges = graph.getEdgeList().size();

        for (int i = 0; i < amountOfEdges; ++i) {
            fileWriter.write(String.valueOf(graph.getIndexOfVertex(graph.getEdgeList().get(i).getSourceVertex())));
            fileWriter.append(' ');
            fileWriter.write(String.valueOf(graph.getIndexOfVertex(graph.getEdgeList().get(i).getTargetVertex())));
            fileWriter.append(' ');
            fileWriter.write(graph.getEdgeList().get(i).getIdentifier());
            fileWriter.append('\n');
        }

    }

    private File getChosenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file directory");
        return fileChooser.showSaveDialog(null);
    }
}
