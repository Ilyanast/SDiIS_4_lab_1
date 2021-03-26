package com.graph.editor.controller;

import com.graph.editor.model.Graph;
import com.graph.editor.model.MainModel;
import com.graph.editor.view.shapes.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LoadGraphFromFile {

    private final MainModel mainModel;
    private final Pane pane;

    private final Graph graph;

    public LoadGraphFromFile(MainModel mainModel, Pane pane) {
        this.mainModel = mainModel;
        this.pane = pane;

        graph = mainModel.getGraph();

        handleLoadGraphFromFile(getChosenFile());
    }

    private void handleLoadGraphFromFile(File chosenFile) {
        pane.getChildren().clear();
        mainModel.getGraph().clear();

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
            System.out.println("File was not selected");;
        }
    }

    private void addALlVertexes(int amountOfVertices, Scanner scanner) {
        for (int i = 0; i < amountOfVertices; ++i) {
            int x_pos = scanner.nextInt();
            int y_pos = scanner.nextInt();

            String identifier = scanner.nextLine();

            Vertex vertex = new Vertex(x_pos, y_pos);
            vertex.setIdentifier(identifier);
            new VertexController(mainModel, vertex);
            graph.addVertex(vertex);
            pane.getChildren().add(vertex.getGroup());
        }
    }

    private void addAllArcs(int amountOfEdges, Scanner scanner) {
        for (int i = 0; i < amountOfEdges; ++i) {

            EdgeType edgeType = EdgeType.values()[scanner.nextInt()];

            Vertex sourceVertex = graph.getGraphList().get(scanner.nextInt()).getVertex();
            Vertex targetVertex = graph.getGraphList().get(scanner.nextInt()).getVertex();

            String identifier = scanner.nextLine();

            Edge edge;

            switch (edgeType) {
                case NOT_ORIENTED_EDGE: {
                    edge = new NotOrientedEdge(sourceVertex);
                    addEdge(sourceVertex, targetVertex, identifier, edge, EdgeType.NOT_ORIENTED_EDGE);
                    break;
                }
                case ORIENTED_EDGE: {
                    edge = new OrientedEdge(sourceVertex);
                    addEdge(sourceVertex, targetVertex, identifier, edge, EdgeType.ORIENTED_EDGE);
                    break;
                }
            }
        }
    }


    private void addEdge(Vertex sourceVertex, Vertex targetVertex, String identifier, Edge edge, EdgeType edgeType) {
        edge.setTargetVertex(targetVertex);
        edge.setIdentifier(identifier);
        edge.addEventHandler(MouseEvent.MOUSE_CLICKED, new EdgeEventHandler(mainModel, edge));

        graph.addEdge(edge, edgeType);
        pane.getChildren().add(edge.getGroup());

        putVerticesOverEdge(sourceVertex);
        putVerticesOverEdge(targetVertex);
    }

    private void putVerticesOverEdge(Vertex vertex) {
        pane.getChildren().remove(vertex.getGroup());
        pane.getChildren().addAll(vertex.getGroup());
    }
}