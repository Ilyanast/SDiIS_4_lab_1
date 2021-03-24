package com.graph.editor.controller;

import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.SelectedElement;
import com.graph.editor.view.shapes.Edge;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class EdgeEventHandler implements EventHandler<MouseEvent> {

    private final SelectedElement selectedElement;
    private final CurrentTool currentTool;
    private final Edge edge;

    public EdgeEventHandler(SelectedElement selectedElement, CurrentTool currentTool, Edge edge) {
        this.selectedElement = selectedElement;
        this.currentTool = currentTool;
        this.edge = edge;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (currentTool.getCurrentTool() == ToolType.HAND_TOOL) {
            selectedElement.setSelectedElement(edge);
        }
    }
}
