package com.graph.editor.controller;

import com.graph.editor.model.CurrentActiveElement;
import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.EdgeTargetVertices;
import com.graph.editor.model.Graph;
import com.graph.editor.view.MainSceneElements;

public class MainController {

    private final MainSceneElements mainSceneElements;
    private CurrentActiveElement currentActiveElement;
    private EdgeTargetVertices edgeTargetVertices;
    private CurrentTool currentTool;
    private Graph graph;

    public MainController(MainSceneElements mainSceneElements) {
        this.mainSceneElements = mainSceneElements;

        createAllModels();
        createPaneController();
        createSceneController();
        createMenuBarController();
        createToolBarController();
    }

    private void createAllModels() {
        graph = new Graph();
        currentTool = new CurrentTool();
        edgeTargetVertices = new EdgeTargetVertices();
        currentActiveElement = new CurrentActiveElement();
    }

    private  void createSceneController() {
        SceneController sceneController = new SceneController(mainSceneElements.getActiveScene(),
                             mainSceneElements.getPane(), graph, currentTool, currentActiveElement, edgeTargetVertices);
    }

    private void createMenuBarController() {
        MenuBarController menuBarController = new MenuBarController(mainSceneElements.getMenuBarItems());
    }

    private void createToolBarController() {
        ToolBarController toolBarController = new ToolBarController(mainSceneElements.getToolBarItems(), currentTool);
    }

    private void createPaneController() {
        PaneController paneController = new PaneController(mainSceneElements.getPane(), graph, currentActiveElement,
                                                                                      currentTool, edgeTargetVertices);
    }

}
