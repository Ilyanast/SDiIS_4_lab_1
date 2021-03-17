package com.graph.editor.controller;

import com.graph.editor.model.CurrentActiveElement;
import com.graph.editor.model.CurrentTool;
import com.graph.editor.view.shapes.Edge;

public class EdgeController {

    //TODO Написать котроллер

    private final CurrentActiveElement currentActiveElement;
    private final CurrentTool currentTool;
    private final Edge edge;

    public EdgeController(CurrentTool currentTool, CurrentActiveElement currentActiveElement, Edge edge) {
        this.currentActiveElement = currentActiveElement;
        this.currentTool = currentTool;
        this.edge = edge;
    }
}
