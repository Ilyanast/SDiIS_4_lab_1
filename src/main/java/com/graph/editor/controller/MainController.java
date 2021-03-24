package com.graph.editor.controller;

import com.graph.editor.model.SelectedElement;
import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.EdgeTargetVertices;
import com.graph.editor.model.Graph;
import com.graph.editor.view.MainSceneElements;

public class MainController {

    private final MainSceneElements mainSceneElements;
    private SelectedElement selectedElement;
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
        selectedElement = new SelectedElement();
    }

    private  void createSceneController() {
        SceneController sceneController = new SceneController(mainSceneElements.getActiveScene(),
                              mainSceneElements.getPane(), graph, currentTool, selectedElement, edgeTargetVertices);
    }

    private void createMenuBarController() {
        MenuBarController menuBarController = new MenuBarController(mainSceneElements.getMenuBarItems(), graph,
                                                                                       mainSceneElements.getPane());
    }

    private void createToolBarController() {
        ToolBarController toolBarController = new ToolBarController(mainSceneElements.getToolBarItems(), currentTool);
    }

    private void createPaneController() {
        PaneController paneController = new PaneController(mainSceneElements.getPane(), graph, selectedElement,
                                                                                      currentTool, edgeTargetVertices);
    }

}
