package com.graph.editor.controller;

import com.graph.editor.model.SelectedElement;
import com.graph.editor.model.CurrentTool;
import com.graph.editor.view.shapes.Edge;
import javafx.scene.input.MouseEvent;

public class EdgeController {

    private final SelectedElement selectedElement;
    private final CurrentTool currentTool;
    private final Edge edge;

    public EdgeController(CurrentTool currentTool, SelectedElement selectedElement, Edge edge) {
        this.selectedElement = selectedElement;
        this.currentTool = currentTool;
        this.edge = edge;

        edge.setOnMouseClicked(this::handleOnMouseClickedEvent);
    }

    private void handleOnMouseClickedEvent(MouseEvent mouseEvent) {
        if (currentTool.getCurrentTool() == ToolType.HAND_TOOL) {
            selectedElement.setSelectedElement(edge);
        }
    }

}
