package com.graph.editor.model;

public class MainModel {

    private final Graph graph;
    private final CurrentTool currentTool;
    private final SelectedElement selectedElement;
    private final EdgeTargetVertices edgeTargetVertices;

    public MainModel() {
        graph = new Graph();
        currentTool = new CurrentTool();
        selectedElement = new SelectedElement();
        edgeTargetVertices = new EdgeTargetVertices();
    }

    public Graph getGraph() {
        return graph;
    }

    public CurrentTool getCurrentTool() {
        return currentTool;
    }

    public SelectedElement getSelectedElement() {
        return selectedElement;
    }

    public EdgeTargetVertices getEdgeTargetVertices() {
        return edgeTargetVertices;
    }
}
