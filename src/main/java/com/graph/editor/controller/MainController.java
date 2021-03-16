package com.graph.editor.controller;

import com.graph.editor.view.MainSceneElements;

public class MainController {

    private final MainSceneElements mainSceneElements;
    private Tool currentTool;

    private void createMenuBarController() {
        MenuBarController menuBarController = new MenuBarController(mainSceneElements.getMenuBarItems());
    }

    private void createToolBarController() {
        ToolBarController toolBarController = new ToolBarController(mainSceneElements.getToolBarItems(), currentTool);
    }

    private void createPaneController() {
        PaneController paneController = new PaneController(mainSceneElements.getPane(), currentTool);
    }

    public MainController(MainSceneElements mainSceneElements) {
        this.mainSceneElements = mainSceneElements;
        currentTool = Tool.VERTEX_TOOL;
        createMenuBarController();
        createToolBarController();
        createPaneController();
    }

}
