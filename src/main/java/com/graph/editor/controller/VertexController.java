package com.graph.editor.controller;

import com.graph.editor.model.SelectedElement;
import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.Graph;
import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class VertexController {

    private final SelectedElement selectedElement;
    private final CurrentTool currentTool;
    private final Vertex vertex;
    private final Graph graph;

    private double firstPressPosX, firstPressPosY;

    public VertexController(CurrentTool currentTool, SelectedElement selectedElement, Graph graph, Vertex vertex) {
        this.selectedElement = selectedElement;
        this.currentTool = currentTool;
        this.vertex = vertex;
        this.graph = graph;

        vertex.setOnMousePressed(this::handleOnMousePressedEvent);
        vertex.setOnMouseDragged(this::handleOnMouseDraggedEvent);
        vertex.setOnMouseReleased(this::handleOnMouseReleasedEvent);
    }


    private void handleOnMousePressedEvent(MouseEvent mouseEvent) {
        if (currentTool.getCurrentTool() == ToolType.HAND_TOOL) {
            selectedElement.setSelectedElement(vertex);
            firstPressPosX = mouseEvent.getSceneX();
            firstPressPosY = mouseEvent.getSceneY();
        }
    }

    private void handleOnMouseReleasedEvent(MouseEvent mouseEvent) {
        if (currentTool.getCurrentTool() == ToolType.HAND_TOOL) {
            vertex.updatePositionWithTranslate();
        }
    }

    private void handleOnMouseDraggedEvent(MouseEvent mouseEvent) {
        if (currentTool.getCurrentTool() == ToolType.HAND_TOOL) {
            double offsetX = mouseEvent.getSceneX() - firstPressPosX;
            double offsetY = mouseEvent.getSceneY() - firstPressPosY;
            vertex.setTranslateX(offsetX);
            vertex.setTranslateY(offsetY);
            updateConnectedEdges(vertex);
        }
    }

    private void updateConnectedEdges(Vertex vertex) {
        List<Edge> edgeList = graph.getListOfConnectedEdges(vertex);
        for(Edge edge : edgeList) {
            edge.updateEdgeCoords();
        }
    }

}

