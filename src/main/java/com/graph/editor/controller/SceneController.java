package com.graph.editor.controller;

import com.graph.editor.model.*;
import com.graph.editor.view.TextInputDialogBuilder;
import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.List;

public class SceneController {

    private final Pane pane;
    private final MainModel mainModel;

    private final EdgeTargetVertices edgeTargetVertices;
    private final SelectedElement selectedElement;
    private final Graph graph;


    public SceneController(Scene activeScene, Pane pane, MainModel mainModel) {
        this.mainModel = mainModel;
        this.pane = pane;

        edgeTargetVertices = mainModel.getEdgeTargetVertices();
        selectedElement = mainModel.getSelectedElement();
        graph = mainModel.getGraph();

        activeScene.setOnKeyPressed(this::selectedElementKeyEvents);
    }


    private void selectedElementKeyEvents(KeyEvent keyEvent) {
        if(selectedElement.isElementSelected()){
            switch (keyEvent.getCode()) {
                case DELETE:
                    deleteSelectedElement();
                    break;
                case I:
                    setIdentifierToSelectedElement();
                    break;
            }
        }
        if(keyEvent.getCode() == KeyCode.ESCAPE) {
            clearEdgeTargetVertices();
        }
    }

    private void deleteSelectedElement() {
        if (selectedElement.getSelectedElement() instanceof Vertex) {
            deleteVertex((Vertex) selectedElement.getSelectedElement());
        }
        else if (selectedElement.getSelectedElement() instanceof Edge) {
            deleteEdge((Edge) selectedElement.getSelectedElement());
        }
    }

    private void setIdentifierToSelectedElement() {
        TextInputDialogBuilder textInputDialogBuilder = new TextInputDialogBuilder();
        if(selectedElement.getSelectedElement() instanceof Vertex) {
            setVertexIdentifier((Vertex) selectedElement.getSelectedElement(), textInputDialogBuilder.getIdentifier());
        }
        else if(selectedElement.getSelectedElement() instanceof Edge) {
            setEdgeIdentifier((Edge) selectedElement.getSelectedElement(), textInputDialogBuilder.getIdentifier());
        }
    }

    private void clearEdgeTargetVertices() {
        if(edgeTargetVertices.isWaitForTargetVertex()) {
            pane.getChildren().remove(edgeTargetVertices.getEdge().getGroup());
            edgeTargetVertices.clear();
        }
    }

    private void setEdgeIdentifier(Edge edge, String identifier) {
        edge.setIdentifier(identifier);
    }

    private void setVertexIdentifier(Vertex vertex, String identifier) {
        vertex.setIdentifier(identifier);
    }

    private void deleteVertex(Vertex vertex) {
        List<Edge> connectedEdges = graph.getListOfConnectedEdges(vertex);
        pane.getChildren().removeIf(elements -> elements.equals(vertex.getGroup()));
        removeConnectedEdgesFromPane(connectedEdges);
        graph.removeVertex(vertex);
        selectedElement.deselectElement();
    }

    private void deleteEdge(Edge edge) {
        pane.getChildren().removeIf(elements -> elements.equals(edge.getGroup()));
        graph.removeEdge(edge);
        selectedElement.deselectElement();
    }

    private void removeConnectedEdgesFromPane(List<Edge> connectedEdges) {
        for (Edge edge : connectedEdges) {
            pane.getChildren().remove(edge.getGroup());
        }
    }
}
