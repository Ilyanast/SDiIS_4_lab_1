package com.graph.editor.controller;

import com.graph.editor.model.SelectedElement;
import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.EdgeTargetVertices;
import com.graph.editor.model.Graph;
import com.graph.editor.view.TextInputDialogBuilder;
import com.graph.editor.view.shapes.Edge;
import com.graph.editor.view.shapes.Vertex;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.List;

public class SceneController {

    //TODO Сделать выбор инструмента по кнопке

    private final Pane pane;
    private final Graph graph;
    private final CurrentTool currentTool;
    private final EdgeTargetVertices edgeTargetVertices;
    private final SelectedElement selectedElement;

    public SceneController(Scene activeScene, Pane pane, Graph graph, CurrentTool currentTool,
                           SelectedElement selectedElement, EdgeTargetVertices edgeTargetVertices) {
        this.selectedElement = selectedElement;
        this.edgeTargetVertices = edgeTargetVertices;
        this.currentTool = currentTool;
        this.graph = graph;
        this.pane = pane;


        activeScene.setOnKeyPressed(this::handleSceneKeyEvents);
    }


    private void handleSceneKeyEvents(KeyEvent keyEvent) {
        switch (currentTool.getCurrentTool()) {
            case HAND_TOOL:
                handleHandToolKeyEvents(keyEvent);
                break;
            case EDGE_TOOL:
                handleEdgeToolKeyEvents(keyEvent);
                break;
        }
    }

    private void handleHandToolKeyEvents(KeyEvent keyEvent) {
        if(selectedElement.isElementSelected()){
            switch (keyEvent.getCode()) {
                case DELETE:
                    handleDeleteKeyEvent();
                    break;
                case I:
                    handleIKeyEvent();
                    break;
            }
        }
    }

    private void handleEdgeToolKeyEvents(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            handleEscapeKeyEvent();
        }
    }

    private void handleDeleteKeyEvent() {
        if (selectedElement.getSelectedElement() instanceof Vertex) {
            deleteVertex((Vertex) selectedElement.getSelectedElement());
        }
        else if (selectedElement.getSelectedElement() instanceof Edge) {
            deleteEdge((Edge) selectedElement.getSelectedElement());
        }
    }

    private void handleIKeyEvent() {
        TextInputDialogBuilder textInputDialogBuilder = new TextInputDialogBuilder();
        if(selectedElement.getSelectedElement() instanceof Vertex) {
            setVertexIdentifier((Vertex) selectedElement.getSelectedElement(), textInputDialogBuilder.getIdentifier());
        }
        else if(selectedElement.getSelectedElement() instanceof Edge) {
            setEdgeIdentifier((Edge) selectedElement.getSelectedElement(), textInputDialogBuilder.getIdentifier());
        }
    }

    private void handleEscapeKeyEvent() {
        if(edgeTargetVertices.isWaitForSecondClick()) {
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
        for (Edge connectedEdge : connectedEdges) {
            pane.getChildren().remove(connectedEdge.getGroup());
        }
    }
}
