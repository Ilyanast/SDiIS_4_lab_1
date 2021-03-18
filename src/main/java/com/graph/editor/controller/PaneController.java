package com.graph.editor.controller;

import com.graph.editor.model.SelectedElement;
import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.EdgeTargetVertices;
import com.graph.editor.model.Graph;
import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class PaneController {

    private final Graph graph;
    private final Pane pane;

    private final SelectedElement selectedElement;
    private final EdgeTargetVertices edgeTargetVertices;
    private final CurrentTool currentTool;

    public PaneController(Pane pane, Graph graph, SelectedElement selectedElement, CurrentTool currentTool,
                          EdgeTargetVertices edgeTargetVertices) {
        this.selectedElement = selectedElement;
        this.edgeTargetVertices = edgeTargetVertices;
        this.currentTool = currentTool;
        this.graph = graph;
        this.pane = pane;

        pane.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseButton.PRIMARY)
            switch (currentTool.getCurrentTool()) {
                case HAND_TOOL:
                    handleVertexToolMouseEvents(mouseEvent);
                    break;
                case EDGE_TOOL:
                    handleEdgeToolMouseEvents(mouseEvent);
                    break;
            }
    }

    private void handleVertexToolMouseEvents(MouseEvent mouseEvent) {
        if(mouseEvent.getTarget().equals(pane)) {
            switch (mouseEvent.getClickCount()) {
                case 1:
                    handleOneClickEvent();
                    break;
                case 2:
                    handleDoubleClickEvent(mouseEvent);
                    break;
            }
        }
    }

    private void handleEdgeToolMouseEvents(MouseEvent mouseEvent) {
        if(mouseEvent.getTarget().getClass().equals(Circle.class)) {
            Vertex vertex = getVertexClickedOn(mouseEvent);
            handleEdgeCreator(vertex);
        }
    }

    private void handleOneClickEvent() {
        selectedElement.deselectElement();
    }

    private void handleDoubleClickEvent(MouseEvent mouseEvent) {
        Vertex vertex = new Vertex(mouseEvent.getX(), mouseEvent.getY());
        VertexController vertexController = new VertexController(currentTool, selectedElement, graph, vertex);
        pane.getChildren().add(vertex.getGroup());
        graph.addVertexToGraph(vertex);
    }

    private Vertex getVertexClickedOn(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getTarget();
        Group group = (Group) circle.getParent();
        return graph.getVertexByGroup(group);
    }

    private void handleEdgeCreator(Vertex vertex) {
        if(edgeTargetVertices.isWaitForSecondClick()) {
            Edge edge = new Edge(edgeTargetVertices.getFirstVertex(), vertex);
            EdgeController edgeController = new EdgeController(currentTool, selectedElement, edge);
            putVerticesOverEdge(vertex, edge);
            edgeTargetVertices.clear();
            graph.addEdge(edge);
        }
        else {
            edgeTargetVertices.setFirstVertex(vertex);
        }
    }

    private void putVerticesOverEdge(Vertex vertex, Edge edge) {
        pane.getChildren().removeAll(edgeTargetVertices.getFirstVertex().getGroup(), vertex.getGroup());
        pane.getChildren().addAll(edge.getGroup(), edgeTargetVertices.getFirstVertex().getGroup(), vertex.getGroup());
    }
}
