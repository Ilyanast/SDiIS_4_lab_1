package com.graph.editor.controller;

import com.graph.editor.model.CurrentTool;
import com.graph.editor.model.Graph;
import com.graph.editor.view.MainSceneElements;

public class MainController {

    private final MainSceneElements mainSceneElements;
    private final Graph graph;
    private final CurrentTool currentTool;

    private void createMenuBarController() {
        MenuBarController menuBarController = new MenuBarController(mainSceneElements.getMenuBarItems());
    }

    private void createToolBarController() {
        ToolBarController toolBarController = new ToolBarController(mainSceneElements.getToolBarItems(), currentTool);
    }

    private void createPaneController() {
        PaneController paneController = new PaneController(mainSceneElements.getPane(), graph, currentTool);
    }

    public MainController(MainSceneElements mainSceneElements, Graph graph) {
        this.mainSceneElements = mainSceneElements;
        this.graph = graph;

        currentTool = new CurrentTool(Tool.VERTEX_TOOL);

        createMenuBarController();
        createToolBarController();
        createPaneController();
    }

}
