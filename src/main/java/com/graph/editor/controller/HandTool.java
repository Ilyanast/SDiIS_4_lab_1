package com.graph.editor.controller;

import com.graph.editor.view.shapes.Vertex;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class HandTool implements Tool {

    private final Pane pane;

    public HandTool(Pane pane) {
        this.pane = pane;
    }

    @Override
    public void handleMouseClick(MouseEvent mouseEvent) {
        switch (mouseEvent.getClickCount()) {
            case 1:
                handleOneClickEvent();
                break;
            case 2:
                handleDoubleClickEvent(mouseEvent);
                break;
        }
    }

    private void handleOneClickEvent() {
        selectedElement.deselectElement();
    }

    private void handleDoubleClickEvent(MouseEvent mouseEvent) {
        Vertex vertex = new Vertex(mouseEvent.getX(), mouseEvent.getY());
        new VertexController(currentTool, selectedElement, graph, vertex);
        pane.getChildren().add(vertex.getGroup());
        graph.addVertexToGraph(vertex);
    }

}
