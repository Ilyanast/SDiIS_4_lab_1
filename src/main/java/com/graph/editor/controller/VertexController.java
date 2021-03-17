package com.graph.editor.controller;

import com.graph.editor.model.CurrentActiveVertex;
import com.graph.editor.view.shapes.Vertex;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class VertexController {

    private final CurrentActiveVertex currentActiveVertex;
    private final Vertex vertex;

    private double firstPressPosX, firstPressPosY;

    public VertexController(CurrentActiveVertex currentActiveVertex, Vertex vertex) {
        this.currentActiveVertex = currentActiveVertex;
        this.vertex = vertex;

        vertex.setOnMousePressed(vertexOnMousePressedEventHandler);
        vertex.setOnMouseDragged(vertexOnMouseDraggedEventHandler);
        vertex.setOnMouseReleased(vertexOnMouseReleasedEventHandler);
    }

    private final EventHandler<MouseEvent> vertexOnMouseReleasedEventHandler =
            new EventHandler<>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    vertex.setNewPos();
                }
            };

    private final EventHandler<MouseEvent> vertexOnMousePressedEventHandler =
            new EventHandler<>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    currentActiveVertex.setCurrentActiveVertex(vertex);

                    firstPressPosX = mouseEvent.getSceneX();
                    firstPressPosY = mouseEvent.getSceneY();
                }
            };

    private final EventHandler<MouseEvent> vertexOnMouseDraggedEventHandler =
            new EventHandler<>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    double offsetX, offsetY;

                    offsetX = mouseEvent.getSceneX() - firstPressPosX;
                    offsetY = mouseEvent.getSceneY() - firstPressPosY;

                    vertex.setTranslateX(offsetX);
                    vertex.setTranslateY(offsetY);
                }
            };

}
