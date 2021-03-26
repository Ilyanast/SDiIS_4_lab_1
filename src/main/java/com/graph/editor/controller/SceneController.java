package com.graph.editor.controller;

import com.graph.editor.model.SelectedElement;
import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.EdgeTargetVertices;
import com.graph.editor.model.Graph;
import com.graph.editor.view.TextInputDialogBuilder;
import com.graph.editor.view.shapes.NotOrientedEdge;
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
        else if (selectedElement.getSelectedElement() instanceof NotOrientedEdge) {
            deleteEdge((NotOrientedEdge) selectedElement.getSelectedElement());
        }
    }

    private void handleIKeyEvent() {
        TextInputDialogBuilder textInputDialogBuilder = new TextInputDialogBuilder();
        if(selectedElement.getSelectedElement() instanceof Vertex) {
            setVertexIdentifier((Vertex) selectedElement.getSelectedElement(), textInputDialogBuilder.getIdentifier());
        }
        else if(selectedElement.getSelectedElement() instanceof NotOrientedEdge) {
            setEdgeIdentifier((NotOrientedEdge) selectedElement.getSelectedElement(), textInputDialogBuilder.getIdentifier());
        }
    }

    private void handleEscapeKeyEvent() {
        if(edgeTargetVertices.isWaitForSecondClick()) {
            pane.getChildren().remove(edgeTargetVertices.getEdge().getGroup());
            edgeTargetVertices.clear();
        }
    }

    private void setEdgeIdentifier(NotOrientedEdge notOrientedEdge, String identifier) {
        notOrientedEdge.setIdentifier(identifier);
    }

    private void setVertexIdentifier(Vertex vertex, String identifier) {
        vertex.setIdentifier(identifier);
    }

    private void deleteVertex(Vertex vertex) {
        List<NotOrientedEdge> connectedNotOrientedEdges = graph.getListOfConnectedEdges(vertex);
        pane.getChildren().removeIf(elements -> elements.equals(vertex.getGroup()));
        removeConnectedEdgesFromPane(connectedNotOrientedEdges);
        graph.removeVertex(vertex);
        selectedElement.deselectElement();
    }

    private void deleteEdge(NotOrientedEdge notOrientedEdge) {
        pane.getChildren().removeIf(elements -> elements.equals(notOrientedEdge.getGroup()));
        graph.removeEdge(notOrientedEdge);
        selectedElement.deselectElement();
    }

    private void removeConnectedEdgesFromPane(List<NotOrientedEdge> connectedNotOrientedEdges) {
        for (NotOrientedEdge connectedNotOrientedEdge : connectedNotOrientedEdges) {
            pane.getChildren().remove(connectedNotOrientedEdge.getGroup());
        }
    }
}
