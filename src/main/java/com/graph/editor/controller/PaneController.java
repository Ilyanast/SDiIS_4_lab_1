package com.graph.editor.controller;

import com.graph.editor.model.*;
import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.EdgeType;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PaneController {

    private final Pane pane;
    private final MainModel mainModel;

    private final Graph graph;
    private final CurrentTool currentTool;
    private final SelectedElement selectedElement;
    private final EdgeTargetVertices edgeTargetVertices;

    public PaneController(Pane pane, MainModel mainModel) {
        this.mainModel = mainModel;
        this.pane = pane;

        graph = mainModel.getGraph();
        currentTool = mainModel.getCurrentTool();
        selectedElement = mainModel.getSelectedElement();
        edgeTargetVertices = mainModel.getEdgeTargetVertices();

        pane.setOnMouseMoved(this::handleMouseMove);
        pane.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseMove(MouseEvent mouseEvent) {
        if(edgeTargetVertices.isWaitForTargetVertex()) {
            edgeTargetVertices.setEndLinePosition(mouseEvent.getX(), mouseEvent.getY());
        }
    }

    private void handleMouseClick(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseButton.PRIMARY) {
            switch (currentTool.getCurrentTool()) {
                case HAND_TOOL: {
                    handToolMouseEvents(mouseEvent);
                    break;
                }
                case EDGE_TOOL: {
                    edgeToolMouseEvents(mouseEvent);
                    break;
                }
                case ORIENTED_EDGE_TOOL: {
                    orientedEdgeToolMouseEvents(mouseEvent);
                    break;
                }
            }
        }

    }

    private void handToolMouseEvents(MouseEvent mouseEvent) {
        if(mouseEvent.getTarget().equals(pane)) {
            switch (mouseEvent.getClickCount()) {
                case 1:
                    selectedElement.deselectElement();
                    break;
                case 2:
                    handleDoubleClickEvent(mouseEvent);
                    break;
            }
        }
    }

    private void handleDoubleClickEvent(MouseEvent mouseEvent) {
        Vertex vertex = new Vertex(mouseEvent.getX(), mouseEvent.getY());
        new VertexController(mainModel, vertex);
        pane.getChildren().add(vertex.getGroup());
        graph.addVertex(vertex);
    }

    private Vertex getTargetVertex(MouseEvent mouseEvent) {
        return graph.getGraphList()
            .stream()
            .map(GraphListElement::getVertex)
            .filter(vertex -> Math.abs(mouseEvent.getX() - vertex.getCircleCenterX()) <= Parameters.CIRCLE_RADIUS &&
                    Math.abs(mouseEvent.getY() - vertex.getCircleCenterY()) <= Parameters.CIRCLE_RADIUS)
            .findFirst()
            .orElse(null);
    }

    private void edgeToolMouseEvents(MouseEvent mouseEvent) {
        if(getTargetVertex(mouseEvent) != null) {
            createEdge(getTargetVertex(mouseEvent), EdgeType.NOT_ORIENTED_EDGE);
        }
    }

    private void orientedEdgeToolMouseEvents(MouseEvent mouseEvent) {
        if(getTargetVertex(mouseEvent) != null) {
            createEdge(getTargetVertex(mouseEvent), EdgeType.ORIENTED_EDGE);
        }
    }

    private void createEdge(Vertex vertex, EdgeType edgeType) {
        if(edgeTargetVertices.isWaitForTargetVertex()) {
            if(edgeTargetVertices.getSourceVertex() != vertex) {
                edgeTargetVertices.setTargetVertex(vertex);
                Edge edge = edgeTargetVertices.getEdge();
                edge.addEventHandler(MouseEvent.MOUSE_CLICKED, new EdgeEventHandler(mainModel, edge));

                if(!graph.addEdge(edge, edgeType)) {
                    pane.getChildren().remove(edgeTargetVertices.getEdge().getGroup());
                    edgeTargetVertices.clear();
                }

                putVertexOverEdge(vertex);
            }
            else {
                pane.getChildren().remove(edgeTargetVertices.getEdge().getGroup());
            }
            edgeTargetVertices.clear();
        }
        else {
            edgeTargetVertices.setSourceVertex(vertex, edgeType);
            pane.getChildren().add(edgeTargetVertices.getEdge().getGroup());
            putVertexOverEdge(vertex);
        }
    }

    private void putVertexOverEdge(Vertex vertex) {
        pane.getChildren().remove(vertex.getGroup());
        pane.getChildren().addAll(vertex.getGroup());
    }

}
