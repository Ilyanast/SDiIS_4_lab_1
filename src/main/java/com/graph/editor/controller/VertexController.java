package com.graph.editor.controller;

import com.graph.editor.model.SelectedElement;
import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.Graph;
import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.input.MouseEvent;

public class VertexController {

    private final SelectedElement selectedElement;
    private final CurrentTool currentTool;
    private final Vertex vertex;
    private final Graph graph;

   private double circleCenterOffsetX, circleCenterOffsetY;

    public VertexController(CurrentTool currentTool, SelectedElement selectedElement, Graph graph, Vertex vertex) {
        this.selectedElement = selectedElement;
        this.currentTool = currentTool;
        this.vertex = vertex;
        this.graph = graph;

        vertex.setOnMousePressed(this::handleOnMousePressedEvent);
        vertex.setOnMouseDragged(this::handleOnMouseDraggedEvent);
    }


    private void handleOnMousePressedEvent(MouseEvent mouseEvent) {
        if (currentTool.getCurrentTool() == ToolType.HAND_TOOL) {
            selectedElement.setSelectedElement(vertex);
            setCircleCenterOffset(mouseEvent.getX(), mouseEvent.getY());
        }
    }

    private void handleOnMouseDraggedEvent(MouseEvent mouseEvent) {
        if (currentTool.getCurrentTool() == ToolType.HAND_TOOL) {
            vertex.setVertexPosition(mouseEvent.getX() - circleCenterOffsetX,mouseEvent.getY() - circleCenterOffsetY);
            updateConnectedEdges(vertex);
        }
    }

    private void updateConnectedEdges(Vertex vertex) {
        graph.getListOfConnectedEdges(vertex)
                .forEach(Edge::updateEdgePosition);
    }

    private void setCircleCenterOffset(double x_pos, double y_pos) {
        circleCenterOffsetX = x_pos - vertex.getCircleCenterX();
        circleCenterOffsetY = y_pos - vertex.getCircleCenterY();
    }

}

