package com.graph.editor.controller;

import com.graph.editor.model.*;
import com.graph.editor.view.MainSceneElements;

public class MainController {

    MainModel mainModel;
    MainSceneElements mainSceneElements;

    public MainController(MainModel mainModel, MainSceneElements mainSceneElements) {
        this.mainSceneElements = mainSceneElements;
        this.mainModel = mainModel;

        createPaneController();
        createSceneController();
        createMenuBarController();
        createToolBarController();
    }

    private  void createSceneController() {
        SceneController sceneController = new SceneController(mainSceneElements.getActiveScene(),
                mainSceneElements.getPane(), mainModel);
    }

    private void createMenuBarController() {
        MenuBarController menuBarController = new MenuBarController(mainSceneElements, mainModel);
    }

    private void createToolBarController() {
        ToolBarController toolBarController = new ToolBarController(mainSceneElements.getToolBarItems(),
                mainSceneElements.getPane(), mainModel);
    }

    private void createPaneController() {
        PaneController paneController = new PaneController(mainSceneElements.getPane(), mainModel);
    }
}
