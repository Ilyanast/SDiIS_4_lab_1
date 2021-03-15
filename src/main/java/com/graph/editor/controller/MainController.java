package com.graph.editor.controller;

import com.graph.editor.view.MainSceneElements;

public class MainController {

    private final MainSceneElements mainSceneElements;

    private void createMenuBarController() {
        MenuBarController menuBarController = new MenuBarController(mainSceneElements.getMenuBarItems());
    }

    private void createToolBarController() {
        ToolBarController toolBarController = new ToolBarController(mainSceneElements.getToolBarItems());
    }

    private void createPaneController() {
        PaneController paneController = new PaneController(mainSceneElements.getPane());
    }

    public MainController(MainSceneElements mainSceneElements) {
        this.mainSceneElements = mainSceneElements;
        createMenuBarController();
        createToolBarController();
        createPaneController();
    }
}
