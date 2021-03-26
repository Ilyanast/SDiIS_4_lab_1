package com.graph.editor.controller;

import com.graph.editor.model.*;
import com.graph.editor.view.shapes.NotOrientedEdge;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PaneController {

    private final Pane pane;
    private final MainModel mainModel;

    public PaneController(Pane pane, MainModel mainModel) {
        this.mainModel = mainModel;
        this.pane = pane;

        pane.setOnMouseMoved(this::handleMouseMove);
        pane.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseMove(MouseEvent mouseEvent) {
        if(edgeTargetVertices.isWaitForSecondClick()) {
            edgeTargetVertices.setLineEndPosition(mouseEvent.getX(), mouseEvent.getY());
        }
    }

    private void handleMouseClick(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseButton.PRIMARY) {
            if(mouseEvent.getClickCount() == 1 && mouseEvent.getTarget().equals(pane)) {
                selectedElement.deselectElement();
            }
            switch (currentTool.getCurrentTool()) {
                case HAND_TOOL:
                    handleVertexToolMouseEvents(mouseEvent);
                    break;
                case EDGE_TOOL:
                    handleEdgeToolMouseEvents(mouseEvent);
                    break;
            }
        }

    }

    private void handleVertexToolMouseEvents(MouseEvent mouseEvent) {
        if(mouseEvent.getTarget().equals(pane)) {
            switch (mouseEvent.getClickCount()) {
                case 1:
                    selectedElement.deselectElement();
                    break;
                case 2:
                    handleDoubleClickEvent(mouseEvent);
                    break;
            }
        }
    }

    private Vertex getTargetVertex(MouseEvent mouseEvent) {
        return graph.getGraphList()
            .stream()
            .map(VertexAndAdjacentVertices::getVertex)
            .filter(vertex -> Math.abs(mouseEvent.getX() - vertex.getCircleCenterX()) <= Parameters.CIRCLE_RADIUS &&
                    Math.abs(mouseEvent.getY() - vertex.getCircleCenterY()) <= Parameters.CIRCLE_RADIUS)
            .findFirst()
            .orElse(null);
    }

    private void handleEdgeToolMouseEvents(MouseEvent mouseEvent) {
        if(getTargetVertex(mouseEvent) != null) {
            handleEdgeCreator(getTargetVertex(mouseEvent));
        }
    }

    private void handleDoubleClickEvent(MouseEvent mouseEvent) {
        Vertex vertex = new Vertex(mouseEvent.getX(), mouseEvent.getY());
        new VertexController(currentTool, selectedElement, graph, vertex);
        pane.getChildren().add(vertex.getGroup());
        graph.addVertexToGraph(vertex);
    }

    private void handleEdgeCreator(Vertex vertex) {
        if(edgeTargetVertices.isWaitForSecondClick()) {
            if(edgeTargetVertices.getSourceVertex() != vertex) {
                edgeTargetVertices.setTargetVertex(vertex);
                NotOrientedEdge notOrientedEdge = edgeTargetVertices.getEdge();
                notOrientedEdge.addEventHandler(MouseEvent.MOUSE_CLICKED, new EdgeEventHandler(selectedElement, currentTool, notOrientedEdge));
                putVerticesOverEdge(vertex);
                graph.addEdge(notOrientedEdge);
            }
            else {
                pane.getChildren().remove(edgeTargetVertices.getEdge().getGroup());
            }
            edgeTargetVertices.clear();
        }
        else {
            edgeTargetVertices.setSourceVertex(vertex);
            pane.getChildren().add(edgeTargetVertices.getEdge().getGroup());
            putVerticesOverEdge(vertex);
        }
    }

    private void putVerticesOverEdge(Vertex vertex) {
        pane.getChildren().remove(vertex.getGroup());
        pane.getChildren().addAll(vertex.getGroup());
    }

}
