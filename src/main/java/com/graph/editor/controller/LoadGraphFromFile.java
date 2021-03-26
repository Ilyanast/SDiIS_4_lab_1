package com.graph.editor.controller;

import com.graph.editor.view.shapes.NotOrientedEdge;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LoadGraphFromFile {

    private final MainController mainController;
    private final Pane pane;

    public LoadGraphFromFile(MainController mainController, Pane pane) {
        this.mainController = mainController;
        this.pane = pane;

        handleLoadGraphFromFile(getChosenFile());
    }

    private void handleLoadGraphFromFile(File chosenFile) {
        pane.getChildren().clear();
        mainController.getGraph().clear();

        addAllComponents(chosenFile);
    }

    private File getChosenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose input file");
        return fileChooser.showOpenDialog(null);
    }

    private void addAllComponents(File chosenFile) {
        try {
            Scanner scanner = new Scanner(chosenFile);

            int amountOfVertices = 0, amountOfEdges = 0;

            if (scanner.hasNextInt()) {
                amountOfVertices = scanner.nextInt();
                amountOfEdges = scanner.nextInt();
            }

            addALlVertexes(amountOfVertices, scanner);
            addAllArcs(amountOfEdges, scanner);

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addAllArcs(int amountOfEdges, Scanner scanner) {
        for (int i = 0; i < amountOfEdges; ++i) {
            int sourceVertexNumber = scanner.nextInt();
            int targetVertexNumber = scanner.nextInt();

            String identifier = scanner.nextLine();

            Vertex sourceVertex = mainController.getGraph().getGraphList().get(sourceVertexNumber).getVertex();
            Vertex targetVertex = mainController.getGraph().getGraphList().get(targetVertexNumber).getVertex();

            NotOrientedEdge notOrientedEdge = new NotOrientedEdge(sourceVertex, sourceVertex.getCircleCenterX(), sourceVertex.getCircleCenterY());
            notOrientedEdge.setTargetVertex(targetVertex);
            notOrientedEdge.setIdentifier(identifier);
            notOrientedEdge.addEventHandler(MouseEvent.MOUSE_CLICKED, new EdgeEventHandler(mainController.getSelectedElement(),
                                                                                mainController.getCurrentTool(), notOrientedEdge));

            mainController.getGraph().addEdge(notOrientedEdge);
            pane.getChildren().add(notOrientedEdge.getGroup());
            putVerticesOverEdge(sourceVertex);
            putVerticesOverEdge(targetVertex);
        }
    }

    private void addALlVertexes(int amountOfVertices, Scanner scanner) {
        for (int i = 0; i < amountOfVertices; ++i) {
            int x_pos = scanner.nextInt();
            int y_pos = scanner.nextInt();

            String identifier = scanner.nextLine();

            Vertex vertex = new Vertex(x_pos, y_pos);
            vertex.setIdentifier(identifier);
            new VertexController(mainController.getCurrentTool(), mainController.getSelectedElement(),
                                                                                mainController.getGraph(), vertex);
            mainController.getGraph().addVertexToGraph(vertex);
            pane.getChildren().add(vertex.getGroup());
        }
    }

    private void putVerticesOverEdge(Vertex vertex) {
        pane.getChildren().remove(vertex.getGroup());
        pane.getChildren().addAll(vertex.getGroup());
    }
}