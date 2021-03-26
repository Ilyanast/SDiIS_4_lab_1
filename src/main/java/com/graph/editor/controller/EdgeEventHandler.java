package com.graph.editor.controller;

import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.MainModel;
import com.graph.editor.model.SelectedElement;
import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.NotOrientedEdge;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class EdgeEventHandler implements EventHandler<MouseEvent> {

    private final MainModel mainModel;
    private final Edge edge;

    private final CurrentTool currentTool;
    private final SelectedElement selectedElement;

    public EdgeEventHandler(MainModel mainModel, Edge edge) {
        this.mainModel = mainModel;
        this.edge = edge;

        currentTool = mainModel.getCurrentTool();
        selectedElement = mainModel.getSelectedElement();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (currentTool.getCurrentTool() == ToolType.HAND_TOOL) {
            selectedElement.setSelectedElement(edge);
        }
    }
}
