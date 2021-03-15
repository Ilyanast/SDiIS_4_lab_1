package com.graph.editor.view;

import javafx.scene.Scene;

public class MainView {

    MainSceneBuilder mainSceneBuilder;
    MainSceneElements mainSceneElements;

    public MainView() {
        mainSceneBuilder = new MainSceneBuilder();
        mainSceneElements = new MainSceneElements();
        setMainSceneElements();
    }

    private void setMainSceneElements() {
        mainSceneElements.setToolBarItems(mainSceneBuilder.getToolBarItems());
        mainSceneElements.setMenuBarItems(mainSceneBuilder.getMenuBarItems());
        mainSceneElements.setPane(mainSceneBuilder.getPane());
    }

    public MainSceneElements getMainSceneElements() {
        return mainSceneElements;
    }

    public Scene getActiveScene() {
        return mainSceneBuilder.getActiveScene();
    }
}
