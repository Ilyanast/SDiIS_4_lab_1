package com.graph.editor.controller;

import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.Graph;
import com.graph.editor.view.shapes.Vertex;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PaneController {

    private final Graph graph;
    private final Pane pane;

    private final CurrentTool currentTool;
    private Vertex currentActiveVertex;

    public PaneController(Pane pane, Graph graph, CurrentTool currentTool) {
        currentActiveVertex = new Vertex(0,0);
        this.currentTool = currentTool;
        this.graph = graph;
        this.pane = pane;
        toolClickHandler();
    }

    private void handleVertexToolClick(MouseEvent mouseEvent) {

        //TODO FIX ActiveVertex;

        if(mouseEvent.getClickCount() == 2 && mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getTarget().equals(pane)) {
            Vertex vertex = new Vertex(mouseEvent.getX(), mouseEvent.getY());
            VertexController vertexController = new VertexController(vertex);
            changeActiveVertex(vertex);
            pane.getChildren().add(vertex.getGroup());
            graph.addVertexToGraph(vertex);
        }
    }

    private void handleEdgeToolClick(MouseEvent mouseEvent) {

    }

    private void handleOrientedEdgeToolClick(MouseEvent mouseEvent) {

    }

    private void toolClickHandler() {
        pane.setOnMouseClicked(mouseEvent -> {
            switch (currentTool.getCurrentTool()) {
                case VERTEX_TOOL:
                    handleVertexToolClick(mouseEvent);
                    break;
                case EDGE_TOOL:
                    handleEdgeToolClick(mouseEvent);
                    break;
                case ORIENTED_EDGE_TOOL:
                    handleOrientedEdgeToolClick(mouseEvent);
                    break;
            }
        });
    }

    private void changeActiveVertex(Vertex vertex) {
        currentActiveVertex.makeVertexInactive();
        currentActiveVertex = vertex;
        vertex.makeVertexActive();
    }

}
