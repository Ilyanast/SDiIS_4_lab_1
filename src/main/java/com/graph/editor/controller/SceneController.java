package com.graph.editor.controller;

import com.graph.editor.model.CurrentActiveElement;
import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.EdgeTargetVertices;
import com.graph.editor.model.Graph;
import com.graph.editor.view.TextInputDialogBuilder;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class SceneController {

    private final Pane pane;
    private final Graph graph;
    private final CurrentTool currentTool;
    private final EdgeTargetVertices edgeTargetVertices;
    private final CurrentActiveElement currentActiveElement;

    public SceneController(Scene activeScene, Pane pane, Graph graph, CurrentTool currentTool,
                           CurrentActiveElement currentActiveElement, EdgeTargetVertices edgeTargetVertices) {
        this.currentActiveElement = currentActiveElement;
        this.edgeTargetVertices = edgeTargetVertices;
        this.currentTool = currentTool;
        this.graph = graph;
        this.pane = pane;

        activeScene.setOnKeyPressed(this::handleSceneKeyEvents);
    }

    private void handleSceneKeyEvents(KeyEvent keyEvent) {
        switch (currentTool.getCurrentTool()) {
            case VERTEX_TOOL:
                handleVertexToolKeyEvents(keyEvent);
                break;
            case EDGE_TOOL:
                handleEdgeToolKeyEvents(keyEvent);
                break;
        }
    }

    private void handleVertexToolKeyEvents(KeyEvent keyEvent) {
        if(currentActiveElement.isAnyVertexActive()){
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
        switch (keyEvent.getCode()) {
            case ESCAPE:
                handleEscapeKeyEvent();
                break;
        }
    }

    private void handleDeleteKeyEvent() {
        pane.getChildren().removeIf(vertices -> vertices.equals(currentActiveElement.getCurrentActiveVertex().getGroup()));
        graph.removeVertex(currentActiveElement.getCurrentActiveVertex());
        currentActiveElement.unselectAllVertices();
    }

    private void handleIKeyEvent() {

        //TODO Сделать switch исходя из типа активного элемента


        TextInputDialogBuilder textInputDialogBuilder = new TextInputDialogBuilder();
        currentActiveElement.getCurrentActiveVertex().setIdentifier(textInputDialogBuilder.getIdentifier());
    }

    private void handleEscapeKeyEvent() {
        edgeTargetVertices.clear();
    }

}
