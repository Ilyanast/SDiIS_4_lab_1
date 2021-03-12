package com.graph.editor.controller;

import com.graph.editor.view.MainSceneBuilder;
import com.graph.editor.view.MainSceneElements;

public class MainController {

    private MainSceneElements mainSceneElements;

    private void createMenuBarController() {
        MenuBarController menuBarController = new MenuBarController(mainSceneElements.getMenuBar());
    }

    private void createToolBarController() {
        ToolBarController toolBarController = new ToolBarController(mainSceneElements.getToolBar());
    }

    public MainController(MainSceneElements mainSceneElements) {
        this.mainSceneElements = mainSceneElements;
        createMenuBarController();
        createToolBarController();

    }

}
