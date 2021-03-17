package com.graph.editor.controller;

import com.graph.editor.model.CurrentActiveElement;
import com.graph.editor.model.CurrentTool;
import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.Vertex;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class VertexController {

    private final CurrentActiveElement currentActiveElement;
    private final CurrentTool currentTool;
    private final Vertex vertex;

    private double firstPressPosX, firstPressPosY;

    public VertexController(CurrentTool currentTool, CurrentActiveElement currentActiveElement, Vertex vertex) {
        this.currentActiveElement = currentActiveElement;
        this.currentTool = currentTool;
        this.vertex = vertex;

        vertex.setOnMousePressed(this::handleVertexToolOnMousePressedEvent);
        vertex.setOnMouseDragged(this::handleVertexToolOnMouseDraggedEvent);
        vertex.setOnMouseReleased(this::handleVertexToolOnMouseReleasedEvent);
    }


    private void handleVertexToolOnMousePressedEvent(MouseEvent mouseEvent) {
        if (currentTool.getCurrentTool() == Tool.VERTEX_TOOL) {
            currentActiveElement.selectActiveVertex(vertex);
            firstPressPosX = mouseEvent.getSceneX();
            firstPressPosY = mouseEvent.getSceneY();
        }
    }

    private void handleVertexToolOnMouseReleasedEvent(MouseEvent mouseEvent) {
        if (currentTool.getCurrentTool() == Tool.VERTEX_TOOL) {
            vertex.updatePositionWithTranslate();
        }
    }

    private void handleVertexToolOnMouseDraggedEvent(MouseEvent mouseEvent) {
        if (currentTool.getCurrentTool() == Tool.VERTEX_TOOL) {
            double offsetX = mouseEvent.getSceneX() - firstPressPosX;
            double offsetY = mouseEvent.getSceneY() - firstPressPosY;
            vertex.setTranslateX(offsetX);
            vertex.setTranslateY(offsetY);
            updateConnectedEdges();
        }
    }

    private void updateConnectedEdges() {
        ObservableList<Node> vertexChildren = vertex.getGroup().getChildren();
        for (Node vertexChild : vertexChildren) {
            System.out.println(vertexChild.getClass().equals(Group.class));
        }
    }

}

